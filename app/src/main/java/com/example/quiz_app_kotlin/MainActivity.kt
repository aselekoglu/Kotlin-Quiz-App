package com.example.quiz_app_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

        val currentUser = auth.currentUser


        if (currentUser != null) {
            val intent = Intent(this, CategoriesActivity::class.java)
            intent.putExtra("user",currentUser.email.toString())
            startActivity(intent)
            finish()
        }


    }


    fun signIn(view: View) {
        val email = emailText.text.toString()
        val password = passwordText.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            //email and password exists
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    val intent = Intent(this, CategoriesActivity::class.java)
                    startActivity(intent)
                    intent.putExtra("user", email)
                    finish()
                }.addOnFailureListener { exception ->
                    Toast.makeText(this@MainActivity, exception.localizedMessage, Toast.LENGTH_LONG)
                        .show()
                }
        } else {
            Toast.makeText(
                this@MainActivity,
                "Please enter your email and password",
                Toast.LENGTH_LONG
            )
        }
    }

    fun signUp(view: View) {
        val email = emailText.text.toString()
        val password = passwordText.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            //email and password exists
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val intent = Intent(this, CategoriesActivity::class.java)
                        startActivity(intent)
                        finish()

                    }
                }.addOnFailureListener { exception ->
                    Toast.makeText(this@MainActivity, exception.localizedMessage, Toast.LENGTH_LONG)
                        .show()
                }


        } else {
            Toast.makeText(
                this@MainActivity,
                "Please enter your email and password",
                Toast.LENGTH_LONG
            )
        }


    }


}