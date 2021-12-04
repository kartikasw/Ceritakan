package com.example.perempuan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.perempuan.R
import com.example.perempuan.model.Post
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class StoryAdapter(options: FirestoreRecyclerOptions<Post>):
    FirestoreRecyclerAdapter<Post, StoryAdapter.MyViewHolder>(options) {

    private val fStore = FirebaseFirestore.getInstance()
    private val reference: CollectionReference = fStore.collection("users")
    private lateinit var modelUsername: String

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var username = itemView.findViewById<TextView>(R.id.tvUsername)
        var title = itemView.findViewById<TextView>(R.id.tvTitle)
        var content = itemView.findViewById<TextView>(R.id.tvContent)
        var likes = itemView.findViewById<TextView>(R.id.likeCount)
        var btnLike = itemView.findViewById<ImageButton>(R.id.btnLike)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_story, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int, model: Post) {
        model.user_uid?.let {
            reference.document(it).get().addOnSuccessListener {
                modelUsername = it.getString("username").toString()
                if(model.status == "Rahasia") {
                    holder.username.text = "Anonymous"
                } else {
                    holder.username.text = modelUsername
                }
            }
        }
        holder.title.text = model.title
        holder.content.text = model.content
        holder.likes.text = model.likeCount.toString()
        holder.btnLike.setOnClickListener{

        }
    }

}