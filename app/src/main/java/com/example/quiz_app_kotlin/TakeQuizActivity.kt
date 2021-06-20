package com.example.quiz_app_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.lang.Exception

class TakeQuizActivity : AppCompatActivity() {

        val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_quiz)

        val questionList = ArrayList<Question>()

        val intent = intent
        val quizID = intent.getStringExtra("quizID")


        try {
            var quiz = db.collection("quizzes").get().addOnSuccessListener { quiz ->

                if (quiz != null) {

//                    var questions = quiz.toCollection("questions").get()
                }
            }


        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

    fun optionClicked(view: View) {
        // when an answer is chosen
    }

}