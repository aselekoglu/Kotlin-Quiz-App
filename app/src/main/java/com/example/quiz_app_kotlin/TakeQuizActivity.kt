package com.example.quiz_app_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class TakeQuizActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_quiz)

        val questionList = ArrayList<Question>()

        val intent = intent
        val quizID = intent.getStringExtra("quizID")

    }

    fun optionClicked(view: View) {
        // when an answer is chosen
    }

}