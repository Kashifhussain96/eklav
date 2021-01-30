package com.mytvapp

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.provider.MediaStore
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setTabAdapter()


    }


    private fun setTabAdapter() {

        val list: MutableList<String>? = ArrayList()
        list?.add("All")
        list?.add("Camera")
        list?.add("Download")
        list?.add("Video")
        for (k in list?.indices!!) {
            tabs.addTab(tabs.newTab().setText(list[k]))
        }
        val adapter = FragmentAdapter(supportFragmentManager, tabs.tabCount, list)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 1
        viewPager.addOnPageChangeListener(TabLayoutOnPageChangeListener(tabs))
        tabs.tabMode = TabLayout.MODE_SCROLLABLE
    }

}