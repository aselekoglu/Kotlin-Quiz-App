package com.example.quiz_app_kotlin

import com.google.firebase.Timestamp

data class Quiz(
    var id: String,
    var description: String,
    var category: String,
    var creator: String,
    var title: String
) {

}