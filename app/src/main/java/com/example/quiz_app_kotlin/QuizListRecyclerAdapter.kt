package com.example.quiz_app_kotlin

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_row.view.*

class QuizListRecyclerAdapter(private val quizList: ArrayList<Quiz>) :
    RecyclerView.Adapter<QuizListRecyclerAdapter.QuizHolder>() {

    class QuizHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var recyclerQuizNameText: TextView? = null
        var recyclerCreatorNameText: TextView? = null
        var recyclerDescriptionText: TextView? = null
        var id: String? = null
        val goQuiz = itemView.startQuizButton

        init {
            recyclerQuizNameText = itemView.findViewById(R.id.quizNameTextRecyclerRow)
            recyclerCreatorNameText = itemView.findViewById(R.id.creatorTextRecyclerRow)
            recyclerDescriptionText = itemView.findViewById(R.id.descriptionTextRecyclerRow)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_row, parent, false)
        return QuizHolder(view)
    }

    override fun onBindViewHolder(holder: QuizHolder, position: Int) {
        holder.recyclerQuizNameText?.text = quizList[position].title
        holder.recyclerCreatorNameText?.text = quizList[position].creator
        holder.recyclerDescriptionText?.text = quizList[position].description
        holder.id = quizList[position].id

        holder.goQuiz.setOnClickListener(View.OnClickListener {
            val intent = Intent(it.context, TakeQuizActivity::class.java)
            intent.putExtra("quizID", holder.id)
            println(holder.id)
            it.context.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return quizList.size
    }


}