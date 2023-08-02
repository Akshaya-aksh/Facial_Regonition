package com.example.firedatabase_assis
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firedatabase_assis.databinding.ActivityWelcomeWindowBinding
import de.hdodenhof.circleimageview.CircleImageView


class welcome_window : AppCompatActivity() {
    private lateinit var bind: ActivityWelcomeWindowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityWelcomeWindowBinding.inflate(layoutInflater)
        setContentView(bind.root)
        val imageView: CircleImageView = findViewById(R.id.imageView)
        // Retrieve user details from Intent extras
        val name = intent.getStringExtra("name")
        val username = intent.getStringExtra("username")
        val age = intent.getStringExtra("age")
        val imageData = intent.getByteArrayExtra("image_data")

        //Display user details in the respective TextViews
        bind.uname.text = name
        bind.tid.text = username
        bind.uage.text = age

        // Convert the image data (BLOB) to a Bitmap and display it in the ImageView
        imageData?.let {
        val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
         bind.imageView.setImageBitmap(bitmap)
        }

        bind.logout.setOnClickListener {
            startActivity(Intent(this, login_form::class.java))

        }
    }
}
