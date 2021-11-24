package com.example.perempuan.activity.post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.perempuan.R
import com.example.perempuan.model.Post

class PostShowActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_show)

        val post = intent.getParcelableExtra<Post>("POST_OBJECT")
        //Log.d("check", "isi: ${post}")
        findViewById<TextView>(R.id.tvStatus).setText(post!!.status)
        findViewById<TextView>(R.id.tvCategory).setText(post!!.category)
        findViewById<TextView>(R.id.tvTitle).setText(post!!.title)
        findViewById<TextView>(R.id.tvContent).setText(post!!.content)

        findViewById<Button>(R.id.btnBack).setOnClickListener{
            onBackPressed()
        }
    }
}