package com.example.perempuan.activity.post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.perempuan.databinding.ActivityPostEditBinding
import com.google.firebase.firestore.FirebaseFirestore

class PostEditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostEditBinding
    private val fStore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val postUid = intent.getStringExtra("UID_STRING").toString()

        var editStatus = binding.tvStatus
        var editCategory = binding.tvCategory
        var editTitle = binding.etTitle
        var editContent = binding.etContent

        fStore.collection("posts").document(postUid).get().addOnSuccessListener {
            editStatus.setText(it.getString("status"))
            editCategory.setText(it.getString("category"))
            editTitle.setText(it.getString("title"))
            editContent.setText(it.getString("content"))
        }

        binding.btnSave.setOnClickListener{
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