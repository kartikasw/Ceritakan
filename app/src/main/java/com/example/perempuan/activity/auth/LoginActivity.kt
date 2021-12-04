package com.example.perempuan.activity.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.perempuan.activity.MainActivity
import com.example.perempuan.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val fAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvRegis.setOnClickListener {
            startActivity(Intent(this, RegisActivity::class.java))
        }

        val loginEmail = binding.etEmail
        val loginPassword = binding.etPassword

        binding.btnLogin.setOnClickListener {
            val email = loginEmail.text.toString()
            val password = loginPassword.text.toString()

            if (email.isEmpty()) {
                loginEmail.setError("Email harus diisi")
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                loginEmail.setError("Email tidak valid")
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                loginPassword.setError("Username harus diisi")
                return@setOnClickListener
            }

            fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Login berhasil.", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Login gagal", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}