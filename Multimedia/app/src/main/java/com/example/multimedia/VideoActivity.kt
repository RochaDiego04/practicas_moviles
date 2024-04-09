package com.example.multimedia

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView


class VideoActivity : AppCompatActivity() {
    private var myVideoView : VideoView? = null
    private var myMediaController: MediaController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        myVideoView = findViewById(R.id.videoView)
        val btnReturnMainActivity = findViewById<Button>(R.id.btnReturnMainActivity)

        setUpVideoPlayer()

        btnReturnMainActivity.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setUpVideoPlayer() {

        if (myMediaController == null) {
            myMediaController = MediaController(this)
            myMediaController!!.setAnchorView(this.myVideoView)
        }

        myVideoView!!.setMediaController(myMediaController)

        myVideoView!!.setVideoURI(
            Uri.parse(
                "android.resource://"
                        + packageName + "/" + R.raw.oceano
            )
        )

        myVideoView!!.requestFocus()

        myVideoView!!.pause()

        myVideoView!!.setOnCompletionListener {
            Toast.makeText(applicationContext, "Video Completed", Toast.LENGTH_SHORT).show()
        }

        myVideoView!!.setOnErrorListener { mp, what, extra ->
            Toast.makeText(applicationContext, "An Error Occured ", Toast.LENGTH_SHORT).show()
            false
        }
    }
}