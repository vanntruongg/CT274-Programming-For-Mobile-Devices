package com.example.b2012051_quizapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class QuizQuestionActivity : AppCompatActivity() {
    private var totalCorrect: Int = 0
    private var currentQuestionIndex: Int = 0
    private var selectedAnswer: Int = -1
    private val questionList = Constants.getQuestions()
    private val totalQuestion = questionList.size
    private var tvOptions: List<TextView> = listOf()

    private var tvQuestion: TextView? = null
    private var image: ImageView? = null
    private var backgroundQuestion: ScrollView? = null

    private var progressBar: ProgressBar? = null
    private var progressBarLabel: TextView? = null

    private var btnSubmit: Button? = null

    private var isSubmitted: Boolean = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

//        Log.i("List question", "${questionList.size}");

        progressBar = findViewById(R.id.progressBar)
        progressBar?.max = questionList.size
        progressBar?.progress = 1

        progressBarLabel = findViewById(R.id.progressBarLabel)
        val currentQuestionNumber = currentQuestionIndex + 1
        progressBarLabel?.text = "$currentQuestionNumber / $totalQuestion"

        tvQuestion = findViewById(R.id.tvQuestion)
        image = findViewById(R.id.image)
        backgroundQuestion = findViewById(R.id.layoutQuestion)
        tvOptions = listOf(
            findViewById(R.id.tvOptionOne),
            findViewById(R.id.tvOptionTwo),
            findViewById(R.id.tvOptionThree),
            findViewById(R.id.tvOptionFour),
        )

        btnSubmit = findViewById(R.id.btnSubmit)

        showCurrentQuestion(currentQuestionIndex)
        selectOption()
        handleBtnSubmit()
    }

    // handle btn submit
    private fun handleBtnSubmit() {
        btnSubmit?.setOnClickListener {
            // get current question
            if(!isSubmitted) {
                if (selectedAnswer < 0 || selectedAnswer > 3) {
                    Toast.makeText(this, "Please choose an answer!", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
                val currentQuestion = questionList[currentQuestionIndex]

                if (selectedAnswer == currentQuestion.correctAnswer) {
                    setBackground(selectedAnswer, R.drawable.correct_border)
                    totalCorrect++
                } else {
                    setBackground(currentQuestion.correctAnswer, R.drawable.correct_border)
                    setBackground(selectedAnswer, R.drawable.wrong_border)
                }

                if (currentQuestionIndex == questionList.size - 1) {
                    btnSubmit?.text = "Finish"
                } else {
                    btnSubmit?.text = "Go to the next question"
                }
                isSubmitted = true
            } else {
                if(currentQuestionIndex < totalQuestion - 1) {
                    showCurrentQuestion(++currentQuestionIndex)

                    val currentQuestionNumber = currentQuestionIndex + 1
                    // set progress
                    progressBar?.progress = currentQuestionNumber
                    // set label progress
                    progressBarLabel?.text = "$currentQuestionNumber / $totalQuestion"

                    selectedAnswer = -1

                    if(currentQuestionIndex > totalQuestion - 1) {
                        btnSubmit?.text = "Finish"
                        isSubmitted = true
                    } else {
                        btnSubmit?.text = "Submit"
                        isSubmitted = false
                    }
                } else {
                    val receivedIntent = intent
                    if (receivedIntent.hasExtra("yourName")) {
                        val yourName = receivedIntent.getStringExtra("yourName")

                        val intent = Intent(this, ResultActivity::class.java)
                        intent.putExtra("yourName", yourName)
                        intent.putExtra("yourScore", totalCorrect.toString())
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }
    }

    private fun selectOption() {
        tvOptions.forEachIndexed { index, textView ->
            textView.setOnClickListener {
                if (!isSubmitted) {
                    if (selectedAnswer != -1) {
                        setBackground(selectedAnswer, R.drawable.default_border)
                    }
                    setBackground(index, R.drawable.selecting_border)
                    selectedAnswer = index
                }
            }
        }
    }

    private fun setBackground(index: Int, drawable: Int) {
        val typeBorder = ContextCompat.getDrawable(this, drawable)
        tvOptions[index].background = typeBorder
    }

    private fun showCurrentQuestion(index: Int) {
        val currentQuestion = questionList[index]

        tvQuestion?.text = currentQuestion.question
        image?.setImageResource(currentQuestion.image)
        val backgroundLayout = ContextCompat.getDrawable(this, currentQuestion.background)
        backgroundQuestion?.background = backgroundLayout

        currentQuestion.options.forEachIndexed { indexOption, option ->
            tvOptions[indexOption].text = option
            setBackground(indexOption, R.drawable.default_border)
        }
    }


}