package com.mytvapp

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.FileUtils
import android.view.View
import android.view.WindowManager
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_video_player.*
import java.io.File
import java.io.IOException


class VideoPlayerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val uri = intent.extras?.getString("uri")
        val videoUri: Uri = Uri.parse(uri)
        player.setVideoURI(videoUri)
        player.setMediaController(MediaController(this))
        player.requestFocus()
        player.start()

    }


}