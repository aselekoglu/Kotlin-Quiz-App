package com.example.quiz_app_kotlin

data class Question(var question: String, var answers: ArrayList<String>, var correctIndex: Int) {
}