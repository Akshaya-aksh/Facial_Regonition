package com.example.firedatabase_assis


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView

class splash : AppCompatActivity() {


    private lateinit var iv_logo:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        iv_logo=findViewById(R.id.iv_logo)



        iv_logo.visibility= View.VISIBLE

        iv_logo.startAnimation(AnimationUtils.loadAnimation(this@splash,android.R.anim.fade_in))


        Handler().postDelayed({

            startActivity(Intent(this@splash,MainActivity::class.java))
             overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        },3000
        )
    }
}