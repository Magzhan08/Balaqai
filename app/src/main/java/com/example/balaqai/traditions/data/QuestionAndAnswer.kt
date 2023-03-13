package com.example.balaqai.traditions.data

data class QuestionAndAnswer(
    val question: String,
    val option1: String,
    val option2: String,
    val option3: String,
    val answer: String,
    var userSeletedAnswer: String,
)
