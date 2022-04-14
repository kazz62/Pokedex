package com.example.pokedex

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewTreeObserver
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

@Suppress("DEPRECATION")
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val splash = findViewById<ImageView>(R.id.SplashScreenImage)
        splash.alpha = 0f
        splash.animate().setDuration(1500).alpha(1f).withEndAction{
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }
    }
}