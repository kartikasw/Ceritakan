package com.example.perempuan.activity.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.perempuan.R
import com.example.perempuan.databinding.ActivityProfileEditBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProfileEditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileEditBinding
    private val fAuth = FirebaseAuth.getInstance()
    val user = fAuth.currentUser
    private val fStore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var editUsername = findViewById<EditText>(R.id.etUsername)

        fStore.collection("users").document(user!!.uid).get().addOnSuccessListener {
            editUsername.setText(it.getString("username"))
        }

        binding.btnSave.setOnClickListener{
            var username = editUsername.text.toString().trim()

            if (username.isEmpty()) {
                editUsername.setError("Username harus diisi")
                return@setOnClickListener
            }

            fStore.collection("users").document(user!!.uid).update("username", username)
            Toast.makeText(this, "Profil berhasil diedit.", Toast.LENGTH_SHORT).show()
            onBackPressed()
        }

    }
}