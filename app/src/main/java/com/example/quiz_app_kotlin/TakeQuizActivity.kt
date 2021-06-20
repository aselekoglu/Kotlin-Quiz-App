package com.example.quiz_app_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_take_quiz.*
import java.lang.Exception

class TakeQuizActivity : AppCompatActivity() {

    val db = Firebase.firestore
    var questionList = mutableListOf<Question>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_quiz)

        val intent = intent

        val quizID = intent.getStringExtra("quizID")

        try {
            if (quizID != null) {

                var quiz = db.collection("quizzes").document(quizID)
                var questions = quiz.collection("questions")

                questions.get().addOnSuccessListener { task ->

                    for (document in task.documents) {
                        if (task != null) {
                            questionList.add(
                                Question(
                                    document.get("question").toString(),
                                    document.get("answers") as ArrayList<String>,
                                    document.get("correctIndex").toString().toInt()
                                )
                            )
                        }
                    }

                    println("Q: $questionList")

                    // for some reason, I couldn't make the adapter work here...
                    var takeQuizAdapter = TakeQuizRecyclerAdapter(questionList)
                    takeQuizRecyclerView.adapter = takeQuizAdapter

                }.addOnFailureListener { e ->
                    e.printStackTrace()
                    println("Failed to fetch data from server.")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun optionClicked(view: View) {
        TODO("To be implemented")
    }

}