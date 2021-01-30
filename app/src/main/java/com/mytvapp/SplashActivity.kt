package com.mytvapp

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.tbruyelle.rxpermissions3.RxPermissions


class SplashActivity : AppCompatActivity() {

    private var rxPermissions : RxPermissions? =  null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        rxPermissions = RxPermissions(this)

       window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)


        if(checkPermission()){
            navigateToMainActivity()
        }else{
            requestPermission()
        }


    }


    private fun navigateToMainActivity(){
        Handler().postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finishAffinity()
        },2000)
    }


    private fun requestPermission(){
        rxPermissions
                ?.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                ?.subscribe { granted ->
                    if (granted) {
                        startActivity(Intent(this,MainActivity::class.java))
                    } else {
                        Toast.makeText(this,"Please provide all the permission to continue",Toast.LENGTH_LONG).show()
                    }
                }
    }

    private fun checkPermission() : Boolean{
        val storage = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)

        return storage == 0

    }
}