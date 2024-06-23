package com.example.blubblubblub

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    private lateinit var animationDrawable: AnimationDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView: ImageView = findViewById(R.id.imageView)
        imageView.setBackgroundResource(R.drawable.frame_animation)
        animationDrawable = imageView.background as AnimationDrawable

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            animationDrawable.start()
        }
    }
}
