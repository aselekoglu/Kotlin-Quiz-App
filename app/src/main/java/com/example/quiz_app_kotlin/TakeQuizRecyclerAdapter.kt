package com.example.quiz_app_kotlin

import android.app.Activity
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TakeQuizRecyclerAdapter(var questionList: MutableList<Question>) :
    RecyclerView.Adapter<TakeQuizRecyclerAdapter.QuestionHolder>() {

    class QuestionHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var questionText: TextView? = null

        var answersArray: ArrayList<String>? = null
        var correctAnswer: Int? = null

        var radioGroup: RadioGroup? = null

        var radioButton1: RadioButton? = null
        var radioButton2: RadioButton? = null
        var radioButton3: RadioButton? = null
        var radioButton4: RadioButton? = null


        init {
            println("holder init")
            questionText = itemView.findViewById(R.id.questionText)
            radioButton1 = itemView.findViewById(R.id.radioButton1)
            radioButton2 = itemView.findViewById(R.id.radioButton2)
            radioButton3 = itemView.findViewById(R.id.radioButton3)
            radioButton4 = itemView.findViewById(R.id.radioButton4)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.question_recycle_row, parent, false)
        println(questionList)
        return QuestionHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionHolder, position: Int) {

        holder.questionText?.text = questionList[position].question
        holder.radioButton1?.text = questionList[position].answers[0]
        holder.radioButton2?.text = questionList[position].answers[1]
        holder.radioButton3?.text = questionList[position].answers[2]
        holder.radioButton4?.text = questionList[position].answers[3]

        var correctAnswer: Int? = holder.correctAnswer

        println(questionList[position].question)
    }

    override fun getItemCount(): Int {
        return questionList.size
    }


}