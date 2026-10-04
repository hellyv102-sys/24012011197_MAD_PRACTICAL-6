package com.example.a24012011197_mad_practical_6

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashActivity : AppCompatActivity(), Animation.AnimationListener {

    lateinit var imglogo: ImageView
    lateinit var gunianimation: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }

        imglogo = findViewById(R.id.imglogo)

        imglogo.setImageResource(R.drawable.uvpce_logo)

        gunianimation = AnimationUtils.loadAnimation(
            this,
            R.anim.twinanimation
        )

        gunianimation.setAnimationListener(this)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        if (hasFocus) {
            imglogo.startAnimation(gunianimation)
        }
    }

    override fun onAnimationEnd(animation: Animation?) {
        Intent(this, MainActivity::class.java).apply {
            startActivity(this)
        }
        finish()
    }

    override fun onAnimationRepeat(animation: Animation?) {
    }

    override fun onAnimationStart(animation: Animation?) {
    }
}