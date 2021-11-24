package com.example.perempuan.activity.post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.example.perempuan.R
import com.example.perempuan.model.Post
import com.google.firebase.firestore.FirebaseFirestore

class PostEditActivity : AppCompatActivity() {

    private val fStore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_edit)

        val postUid = intent.getStringExtra("UID_STRING").toString()

        var editStatus = findViewById<TextView>(R.id.tvStatus)
        var editCategory = findViewById<TextView>(R.id.tvCategory)
        var editTitle = findViewById<TextView>(R.id.etTitle)
        var editContent = findViewById<TextView>(R.id.etContent)

        fStore.collection("posts").document(postUid).get().addOnSuccessListener {
            editStatus.setText(it.getString("status"))
            editCategory.setText(it.getString("category"))
            editTitle.setText(it.getString("title"))
            editContent.setText(it.getString("content"))
        }

        findViewById<Button>(R.id.btnSave).setOnClickListener{
            var title = editTitle.text.toString()
            var content = editContent.text.toString()

            if (title.isEmpty()) {
                editTitle.setError("Judul harus diisi")
                return@setOnClickListener
            }
            if (title.length > 20) {
                editTitle.setError("Judul tidak boleh lebih dari 20 karakter")
                return@setOnClickListener
            }
            if (content.isEmpty()) {
                editTitle.setError("Isi konten harus diisi")
                return@setOnClickListener
            }

            fStore.collection("posts").document(postUid).update(mapOf(
                "title" to title,
                "content" to content
            ))
            Toast.makeText(this, "Post berhasil diedit.", Toast.LENGTH_SHORT).show()
            onBackPressed()
        }
    }
}