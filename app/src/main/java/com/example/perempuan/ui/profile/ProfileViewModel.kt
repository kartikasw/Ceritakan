package com.example.perempuan.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.perempuan.model.Post
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class ProfileViewModel : ViewModel() {

    private val fAuth = FirebaseAuth.getInstance()
    val user = fAuth.currentUser
    private val fStore = FirebaseFirestore.getInstance()
    private val reference: Query = fStore.collection("posts").whereEqualTo("user_uid", user!!.uid)

    fun getPost(): FirestoreRecyclerOptions<Post> {
        return FirestoreRecyclerOptions.Builder<Post>()
            .setQuery(reference, Post::class.java)
            .build();
    }
}