package com.example.perempuan.activity.story

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perempuan.model.Story
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class StoryCreateViewModel: ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private var isSucceeded: Boolean = true

    fun createStory(story: Story) = viewModelScope.launch{
        db.collection("stories").add(story).addOnSuccessListener {
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