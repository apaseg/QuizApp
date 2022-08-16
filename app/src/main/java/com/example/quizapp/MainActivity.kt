package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val about = findViewById<Button>(R.id.btnAbout)
        val start = findViewById<Button>(R.id.btnStart)
        val etName = findViewById<EditText>(R.id.etName)


        about.setOnClickListener{
            val intent = Intent(this,DeveloperInformation::class.java)
            startActivity(intent)
        }

        start.setOnClickListener{
            if(etName.text.toString().trim().isEmpty()){
                Toast.makeText(this,"First Enter Your Name to Start a Quiz",Toast.LENGTH_SHORT).show()
            }
            else{
                val intent = Intent(this,QuestionScreen::class.java)
                val name = etName.text.toString()
                intent.putExtra("name_key",name)
                startActivity(intent)
            }
        }
    }
}