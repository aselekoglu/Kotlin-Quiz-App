package com.example.quiz_app_kotlin

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TakeQuizRecyclerAdapter(var questionList: ArrayList<Question>, var activity: Activity) :
    RecyclerView.Adapter<TakeQuizRecyclerAdapter.QuestionHolder>() {

    class QuestionHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var questionText: TextView? = null


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: QuestionHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


}