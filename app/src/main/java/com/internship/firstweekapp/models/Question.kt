package com.internship.firstweekapp.models

interface Question {
    val question: String
    fun check(): Boolean
    fun getUserAnswer(): String
    fun hideMsg()
}

interface QuestionWithAnswers : Question {
    val answers: ArrayList<String>
}