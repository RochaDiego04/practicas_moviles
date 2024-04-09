package com.example.multimedia

import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import java.io.IOException


class AudioActivity : AppCompatActivity() {

    private lateinit var btnPlay: Button
    private lateinit var btnPause: Button
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio)

        btnPlay = findViewById(R.id.btnPlay)
        btnPause = findViewById(R.id.btnPause)
        val btnReturnMainActivity = findViewById<Button>(R.id.btnReturnMainActivity)

        btnPlay.setOnClickListener {
            playAudioFromRaw()
        }

        btnPause.setOnClickListener {
            pauseAudio()
        }

        btnReturnMainActivity.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun playAudioFromRaw() {
        mediaPlayer = MediaPlayer.create(this, R.raw.cancion)
        mediaPlayer?.start()

        Toast.makeText(this, "Audio started playing", Toast.LENGTH_SHORT).show()
    }

    private fun pauseAudio() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.pause()
                Toast.makeText(this, "Audio paused", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Audio has not played", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}