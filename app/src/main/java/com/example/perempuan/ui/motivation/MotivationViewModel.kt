package com.example.perempuan.ui.motivation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.perempuan.model.Post
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class MotivationViewModel : ViewModel() {

    private val fStore = FirebaseFirestore.getInstance()
    private val reference: Query = fStore.collection("posts").whereEqualTo("category", "Motivasi")

    fun getMotivation(): FirestoreRecyclerOptions<Post> {
        return FirestoreRecyclerOptions.Builder<Post>()
            .setQuery(reference, Post::class.java)
            .build();
    }
}