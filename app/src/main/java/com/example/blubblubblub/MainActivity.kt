package com.example.blubblubblub

import android.graphics.drawable.AnimationDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {

    private lateinit var editText: EditText
    private lateinit var button: Button
    private lateinit var animationDrawable: AnimationDrawable
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var chime: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView: ImageView = findViewById(R.id.imageView)
        imageView.setBackgroundResource(R.drawable.frame_animation)
        animationDrawable = imageView.background as AnimationDrawable

        mediaPlayer = MediaPlayer.create(this, R.raw.brook)
        chime = MediaPlayer.create(this, R.raw.chime)

        editText = findViewById(R.id.editText)

        button = findViewById(R.id.button)
        button.setOnClickListener {
            clearAndStartAnimation()
        }
    }

    private fun clearAndStartAnimation() {
        button.visibility = View.GONE
        editText.visibility = View.GONE
        hideKeyboard()
        chime.start()
        mediaPlayer.start()
        animationDrawable.start()
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, 0)
    }
}
