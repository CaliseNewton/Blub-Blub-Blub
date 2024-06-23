package com.example.blubblubblub

import android.graphics.drawable.AnimationDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    private lateinit var animationDrawable: AnimationDrawable
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView: ImageView = findViewById(R.id.imageView)
        imageView.setBackgroundResource(R.drawable.frame_animation)
        animationDrawable = imageView.background as AnimationDrawable

        mediaPlayer = MediaPlayer.create(this, R.raw.brook)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            (button.parent as? ViewGroup)?.removeView(button)
            mediaPlayer.start()
            animationDrawable.start()
        }
    }
}
