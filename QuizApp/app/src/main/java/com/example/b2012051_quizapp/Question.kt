package com.example.b2012051_quizapp

data class Question(
    val id:Int,
    val question: String,
    val image: Int,
    val background: Int,
    val options: List<String>,
//    val optionOne: String,
//    val optionTwo: String,
//    val optionThree: String,
//    val optionFour: String,
    val correctAnswer: Int
)
