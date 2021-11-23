package com.example.perempuan.activity.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perempuan.model.Post
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class PostCreateViewModel: ViewModel() {
    private val fStore = FirebaseFirestore.getInstance()
    private var isSucceeded: Boolean = true

    fun createPost(uid: String, post: Post) = viewModelScope.launch{
        fStore.collection("posts").document(uid).set(post).addOnSuccessListener {
            //Log.d("check", "Berhasil")
            isSucceeded = true
        }.addOnFailureListener{
            //Log.d("check", "${it.message}")
            isSucceeded = false
        }
    }

    fun getState(): Boolean {
        return isSucceeded
    }
}