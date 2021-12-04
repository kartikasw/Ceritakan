package com.example.perempuan.activity.post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.perempuan.databinding.ActivityPostShowBinding
import com.example.perempuan.model.Post

class PostShowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = intent.getParcelableExtra<Post>("POST_OBJECT")
        //Log.d("check", "isi: ${post}")
        binding.tvStatus.setText(post!!.status)
        binding.tvCategory.setText(post!!.category)
        binding.tvTitle.setText(post!!.title)
        binding.tvContent.setText(post!!.content)

        binding.btnBack.setOnClickListener{
            onBackPressed()
        }
    }
}