package com.example.blubblubblub

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
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
    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var frameAnimation: AnimationDrawable
    private lateinit var newAnimation: AnimationDrawable
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var chime: MediaPlayer
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView= findViewById(R.id.imageView)
        imageView.setBackgroundResource(R.drawable.frame_animation)
        frameAnimation = imageView.background as AnimationDrawable

        mediaPlayer = MediaPlayer.create(this, R.raw.brook)
        chime = MediaPlayer.create(this, R.raw.chime)

        editText = findViewById(R.id.editText)

        startButton = findViewById(R.id.startButton)
        stopButton = findViewById(R.id.stopButton)
        stopButton.visibility = View.GONE


        startButton.setOnClickListener {
            clearAndStartAnimation()
            stopButton.visibility = View.VISIBLE
        }

        stopButton.setOnClickListener {
            stopButton.visibility = View.GONE
            handleStopButtonPressed()
        }

    }

    private fun handleStopButtonPressed() {
        val fadeOut = ObjectAnimator.ofFloat(imageView, "alpha", 1f, 0f)
        fadeOut.duration = 300 // 0.3 seconds
        fadeOut.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                frameAnimation.stop()
                startNewAnimation()
                fadeIn()
            }
        })
        fadeOut.start()
    }

    private fun startNewAnimation() {
        imageView.setBackgroundResource(R.drawable.let_it_go_animation)
        newAnimation = imageView.background as AnimationDrawable
        newAnimation.start()
    }

    private fun fadeIn() {
        val fadeIn = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f)
        fadeIn.duration = 300 // 0.3 seconds
        fadeIn.start()
    }

    private fun clearAndStartAnimation() {
        startButton.visibility = View.GONE
        editText.visibility = View.GONE
        hideKeyboard()
        chime.start()
        mediaPlayer.start()
        frameAnimation.start()
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, 0)
    }
}
