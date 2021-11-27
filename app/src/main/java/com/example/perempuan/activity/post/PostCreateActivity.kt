package com.example.perempuan.activity.post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.perempuan.databinding.ActivityPostCreateBinding
import com.example.perempuan.model.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class PostCreateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostCreateBinding
    private val fAuth = FirebaseAuth.getInstance()
    private val fStore = FirebaseFirestore.getInstance()
    private lateinit var postCreateViewModel: PostCreateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        postCreateViewModel = ViewModelProvider(this).get(PostCreateViewModel ::class.java)

        binding.apply {
            btnCreate.setOnClickListener{
                val status = spStatus.selectedItem.toString()
                val category = spCategory.selectedItem.toString()
                val title = etTitle.text.toString()
                val content = etContent.text.toString()
                val user_uid = fAuth.currentUser?.uid
                val ref = fStore.collection("posts").document()

                if (title.isEmpty()) {
                    etTitle.setError("Judul harus diisi")
                    return@setOnClickListener
                }
                if (category == "Kisah" && title.length > 20) {
                    etTitle.setError("Judul tidak boleh lebih dari 20 karakter")
                    return@setOnClickListener
                }
                if (category == "Motivasi" && title.length > 30) {
                    etTitle.setError("Judul tidak boleh lebih dari 30 karakter")
                    return@setOnClickListener
                }
                if (content.isEmpty()) {
                    etContent.setError("Isi konten harus diisi")
                    return@setOnClickListener
                }
                if (category == "Motivasi" && content.length > 200) {
                    etContent.setError("Isi konten tidak boleh lebih dari 190 karakter")
                    return@setOnClickListener
                }

                var post = user_uid?.let { it -> Post(ref.getId(), it, status, category, title, content,  0) }
                if (post != null) {
                    postCreateViewModel.createPost(ref.getId(), post)
                    if(postCreateViewModel.getState()){
                        Toast.makeText(baseContext, "Berhasil membuat kisah",
                            Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(baseContext, "Gagal membuat kisah",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}