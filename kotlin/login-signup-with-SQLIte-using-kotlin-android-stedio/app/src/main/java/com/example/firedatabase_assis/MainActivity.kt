package com.example.firedatabase_assis
import android.content.ActivityNotFoundException
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

import com.example.firedatabase_assis.databinding.ActivityMainBinding
import com.example.firedatabase_assis.login_form
import java.io.ByteArrayOutputStream

import DB_class
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var imageView: ImageView
    private lateinit var button: Button

    private val REQUEST_IMAGE_CAPTURE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)





        var byteArray: ByteArray = ByteArray(0)
        val dbhelp = DB_class(applicationContext)
        val db = dbhelp.writableDatabase

        imageView = findViewById(R.id.imageView)
        button = findViewById(R.id.profile)

        button.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            try {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "Error: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnrgs.setOnClickListener {
            var name = binding.ed1.text.toString()
            var username = binding.ed2.text.toString()
            var password = binding.ed3.text.toString()
            var age = binding.ed4.text.toString()




            if (name.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty() && age.isNotEmpty()) {
                var data = ContentValues()
                data.put("name", name)
                data.put("username", username)
                data.put("pswd", password)
                data.put("age", age)

                val imageBitmap = (imageView.drawable as? BitmapDrawable)?.bitmap
                imageBitmap?.let {
                 val stream = ByteArrayOutputStream()
                imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
                var byteArray = stream.toByteArray()
                data.put("image_data", byteArray)
                }

                var rs: Long = dbhelp.insertuser(data)

                if (!rs.equals(-1)) {
                    var ad = AlertDialog.Builder(this)
                    ad.setTitle("Message")
                    ad.setMessage("Account registered successfully")
                    ad.setPositiveButton("Ok", null)
                    ad.show()
                    binding.ed1.text.clear()
                    binding.ed2.text.clear()
                    binding.ed3.text.clear()
                    binding.ed4.text.clear()
                    imageView.setImageResource(android.R.color.transparent)
                } else {
                    val ad = AlertDialog.Builder(this)
                    ad.setTitle("Message")
                    ad.setMessage("Record not added")
                    ad.setPositiveButton("Ok", null)
                    ad.show()
                    binding.ed1.text.clear()
                    binding.ed2.text.clear()
                    binding.ed3.text.clear()
                    binding.ed4.text.clear()
                }?: Toast.makeText(this,"Error:Unable to vapture image",Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "All fields required", Toast.LENGTH_SHORT).show()
            }
        }

        binding.loginLink.setOnClickListener {
            val intent = Intent(this, login_form::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(imageBitmap)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}