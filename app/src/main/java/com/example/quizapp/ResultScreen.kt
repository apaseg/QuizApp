package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_screen)

        val correctAnswer : TextView= findViewById(R.id.tvCorrectAnswer)
        val wrongAnswer : TextView = findViewById(R.id.tvWrongAnswer)
        val finalScore : TextView = findViewById(R.id.tvFinalScore)
        val restart : Button = findViewById(R.id.btnRestart)

        val rac = intent.getStringExtra("rac")
        val wac = intent.getStringExtra("wac")

        correctAnswer.text = "Correct Answer: $rac"
        wrongAnswer.text = "Wrong Answer: $wac"
        finalScore.text = "Final Score: $rac"

        restart.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}