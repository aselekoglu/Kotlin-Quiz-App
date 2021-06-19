package com.example.quiz_app_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewParent
import android.view.WindowId
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_quiz_list.*
import java.text.FieldPosition
import java.text.ParsePosition

class QuizListActivity : AppCompatActivity() {

    val db = Firebase.firestore
    val quizList = ArrayList<Quiz>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_list)

        var layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val intent = intent
        val categoryTitle = intent.getStringExtra("title")

        // get quizzes by chosen category
        if (categoryTitle != null) {
            getCategoryDetails(categoryTitle)
        }
    }

    fun getCategoryDetails(categoryTitle: String) {

        val category = db.collection("categories").document(categoryTitle)
        category.get().addOnSuccessListener { category ->
            if (category != null) {
                val quizArray = category["quizzes"] as List<String>
                println(quizArray + quizArray.size)
                var i = 0;
                getQuizDetails(quizArray)
            }
        }.addOnFailureListener { e ->
            e.printStackTrace()
        }
    }

    fun getQuizDetails(quizIDList: List<String>) {
        for (quizID in quizIDList) {
            val quizDetails = db.collection("quizzes").document(quizID)
            println(quizID)
            quizDetails.get().addOnSuccessListener { quiz ->
                val quizObject = Quiz(
                    quizID,
                    quiz["description"].toString(),
                    quiz["category"].toString(),
                    quiz["creator"].toString(),
                    quiz["title"].toString(),
//                    quiz["questions"] as ArrayList<Question>
                )

                if (quizObject != null) {
                    quizList.add(quizObject)
//                    println("object added")
                }
                var recyclerAdapter = QuizListRecyclerAdapter(quizList)
                recyclerView.adapter = recyclerAdapter
//                println("Quiz List: " + quizList)
            }.addOnFailureListener { e ->
                e.printStackTrace()
                println("Failed to fetch the data from server.")
            }
        }
    }
}