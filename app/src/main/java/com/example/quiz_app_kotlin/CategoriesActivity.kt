package com.example.quiz_app_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_categories.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.lang.Exception
import java.util.logging.Logger

class CategoriesActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        val intent = intent
        var categoriesList = ArrayList<String>()

        auth = Firebase.auth

        val currentUser = intent.getStringExtra("user")
        categoriesText.text = currentUser

        // get categories
        try {

            db.collection("categories").get().addOnSuccessListener { result ->
                for (category in result) {
                    categoriesList.add(category["title"].toString())
                }
                var categoriesListView = findViewById<ListView>(R.id.listViewCategories)

                val adapter =
                    ArrayAdapter(this, android.R.layout.simple_list_item_1, categoriesList)
                categoriesListView.adapter = adapter

                categoriesListView.onItemClickListener =
                    AdapterView.OnItemClickListener { parent, view, position, id ->
                        // send the user to Quiz List

                        val intent = Intent(this, QuizListActivity::class.java)
                        intent.putExtra("title", categoriesList.get(position))
                        startActivity(intent)
                    }

            }.addOnFailureListener { exception ->
                Toast.makeText(
                    this@CategoriesActivity,
                    exception.localizedMessage,
                    Toast.LENGTH_LONG
                )
                    .show()
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}