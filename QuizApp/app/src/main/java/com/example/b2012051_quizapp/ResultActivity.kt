package com.example.b2012051_quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val questionList = Constants.getQuestions()

        val imageCup = findViewById<ImageView>(R.id.imageCup)
        val tvName = findViewById<TextView>(R.id.tvName)
        val tvScore = findViewById<TextView>(R.id.tvScore)
        val btnFinish = findViewById<Button>(R.id.btnFinish)

        imageCup.setImageResource(R.drawable.ic_cup)

        val receivedIntent = intent
        if (receivedIntent.hasExtra("yourName") && receivedIntent.hasExtra("yourScore")) {
            val yourName = intent.getStringExtra("yourName")
            val score = intent.getStringExtra("yourScore")
            tvName.text = yourName
            val messageScore = "Your Score is $score out of ${questionList.size}"
            tvScore.text = messageScore
        }

        btnFinish.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}