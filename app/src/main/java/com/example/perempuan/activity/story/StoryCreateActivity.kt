package com.example.perempuan.activity.story

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.perempuan.databinding.ActivityStoryCreateBinding
import com.example.perempuan.model.Story
import com.example.perempuan.ui.profile.ProfileViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_story.*

class StoryCreateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStoryCreateBinding
    private val fAuth = FirebaseAuth.getInstance()
    private lateinit var storyCreateViewModel: StoryCreateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoryCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        storyCreateViewModel = ViewModelProvider(this).get(StoryCreateViewModel ::class.java)

        binding.apply {
            btnCreate.setOnClickListener{
                val status = spStatus.selectedItem.toString()
                val title = etTitle.text.toString()
                val desc = etDesc.text.toString()
                val user_uid = fAuth.currentUser?.uid

                var story = user_uid?.let { it -> Story(it, status, title, desc,  0) }
                if (story != null) {
                    storyCreateViewModel.createStory(story)
                    if(storyCreateViewModel.getState()){
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