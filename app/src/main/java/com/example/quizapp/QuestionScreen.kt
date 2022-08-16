package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.example.quizapp.databinding.ActivityQuestionScreenBinding

class QuestionScreen : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionScreenBinding

    private var rightAnswer: String? = null
    private var rightAnswerCount = 0
    private var quizCount = 1
    private val QUIZ_COUNT = 5

    private val quizData = mutableListOf(
        mutableListOf( "Which method can be defined only once in a program?","main method","static method","private method","finalize method"),
        mutableListOf("Which keyword is used by method to refer to the current object that invoked it?","this","import","catch","abstract"),
        mutableListOf("Which of these access specifiers can be used for an interface?","public","protected","private","All of the mentioned"),
        mutableListOf("Which of the following is correct way of importing an entire package‘pkg’?","import pkg.*","Import pkg.","Import pkg.*","import pkg."),
        mutableListOf("What is the return type of Constructors?","None of the mentioned","int","float","void")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_question_screen)

        binding = ActivityQuestionScreenBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        val tvGreet = findViewById<TextView>(R.id.tvGreet)


        val name = intent.getStringExtra("name_key")

        tvGreet.text = "Hello $name"

        quizData.shuffle()

        showNextQuiz()

    }

    private fun showNextQuiz(){

        val quit : Button = findViewById(R.id.btnQuit)
        val nextQuestion : Button = findViewById(R.id.btnNext)

        binding.tvQuestionCount.text = getString(R.string.Question_count,quizCount)

        val quiz = quizData[0]

        binding.tvQuestion.text = quiz[0]
        rightAnswer = quiz[1]

        quiz.removeAt(0)

        quiz.shuffle()

        binding.rbOption1.text = quiz[0]
        binding.rbOption2.text = quiz[1]
        binding.rbOption3.text = quiz[2]
        binding.rbOption4.text = quiz[3]

        quizData.removeAt(0)

        nextQuestion.setOnClickListener {
            Toast.makeText(this,"Please Answer the Question first!",Toast.LENGTH_SHORT).show()
        }
        quit.setOnClickListener {
            Toast.makeText(this,"Please Answer the Question first!.",Toast.LENGTH_SHORT).show()
        }

    }
    var flag = 0
    fun checkAnswer(view: View){

            val answerBtn: Button = findViewById(view.id)
            val btnText = answerBtn.text.toString()

        val quit : Button = findViewById(R.id.btnQuit)
        val nextQuestion : Button = findViewById(R.id.btnNext)

        var scoreNumber : TextView = findViewById(R.id.tvScoreNumber)


        nextQuestion.setOnClickListener {

                if (btnText == rightAnswer) {
                    rightAnswerCount++

                    Toast.makeText(this, "Right", Toast.LENGTH_SHORT).show()

                    scoreNumber.text = rightAnswerCount.toString()
                } else {
                    Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show()
                }
                checkQuizCount()


        }

        quit.setOnClickListener {

                if(btnText == rightAnswer){
                rightAnswerCount++

                Toast.makeText(this,"Right",Toast.LENGTH_SHORT).show()

                scoreNumber.text = rightAnswerCount.toString()
            }
            else{
                Toast.makeText(this,"Wrong",Toast.LENGTH_SHORT).show()
            }
            flag = 1
            checkQuizCount()
        }
    }

    private fun checkQuizCount(){
        if(quizCount == QUIZ_COUNT || flag == 1){
            val intent = Intent(this,ResultScreen::class.java)
            val rAC = rightAnswerCount.toString()
            val wrongAnswer = quizCount - rightAnswerCount
            val wAC = wrongAnswer.toString()

            intent.putExtra("rac",rAC)
            intent.putExtra("wac",wAC)
            startActivity(intent)
        }
        else{
            quizCount++

            val rgChoices : RadioGroup = findViewById(R.id.rgChoices)

            rgChoices.clearCheck()
            showNextQuiz()
        }
    }
}