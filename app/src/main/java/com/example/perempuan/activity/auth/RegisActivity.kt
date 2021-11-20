package com.example.perempuan.activity.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.perempuan.R
import com.example.perempuan.activity.MainActivity
import com.example.perempuan.databinding.ActivityRegisBinding
import com.example.perempuan.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisActivity : AppCompatActivity() {

    private val fAuth = FirebaseAuth.getInstance()
    private val fStore = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regis)

        findViewById<TextView>(R.id.tvLogin).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        val regisUsername = findViewById<EditText>(R.id.etUsername)
        val regisEmail = findViewById<EditText>(R.id.etEmail)
        val regisPassword = findViewById<EditText>(R.id.etPassword)

        findViewById<Button>(R.id.btnRegis).setOnClickListener{

            val username = regisUsername.text.toString()
            val email = regisEmail.text.toString()
            val password = regisPassword.text.toString()

            if (email.isEmpty()) {
                regisEmail.setError("Email harus diisi")
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                regisEmail.setError("Email tidak valid")
                return@setOnClickListener
            }
            if (username.isEmpty()) {
                regisUsername.setError("Username harus diisi")
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                regisPassword.setError("Username harus diisi")
                return@setOnClickListener
            }
            if (password.length < 6) {
                regisPassword.setError("Password harus lebih dari 6 karakter")
                return@setOnClickListener
            }

            fAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = fAuth.currentUser
                        saveUserData(user!!.uid, username, email)
                        startActivity(Intent(this, MainActivity::class.java))
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.d("cek", "${task.exception}")
                        Toast.makeText(baseContext, "Daftar gagal.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private fun saveUserData(uid: String, username: String, email: String) {
        val data = fStore.collection("users").document(uid)
        val userData = User(uid, username, email)

        data.set(userData).addOnCompleteListener { task ->
            Toast.makeText(baseContext, "Daftar berhasil", Toast.LENGTH_SHORT).show()
        } .addOnFailureListener { exception ->
            //Log.d("cek", "${exception}")
            Toast.makeText(baseContext, "Daftar gagal", Toast.LENGTH_SHORT).show()
        }
    }
}