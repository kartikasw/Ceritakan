package com.example.perempuan.adapter

import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.perempuan.R
import com.example.perempuan.activity.post.PostEditActivity
import com.example.perempuan.activity.post.PostShowActivity
import com.example.perempuan.model.Post
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore

class ProfileAdapter(options: FirestoreRecyclerOptions<Post>):
    FirestoreRecyclerAdapter<Post, ProfileAdapter.MyViewHolder>(options) {

    private val fStore = FirebaseFirestore.getInstance()

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var title = itemView.findViewById<TextView>(R.id.tvTitle)
        var show = itemView.findViewById<Button>(R.id.btnShow)
        var edit = itemView.findViewById<Button>(R.id.btnEdit)
        var delete = itemView.findViewById<Button>(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post_user, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int, model: Post) {
        holder.title.text = model.title

        holder.show.setOnClickListener{
            val intent = Intent(holder.show.context, PostShowActivity::class.java)
            intent.putExtra("POST_OBJECT", model)
            holder.show.context.startActivity(intent)
        }

        holder.edit.setOnClickListener{
            val intent = Intent(holder.edit.context, PostEditActivity::class.java)
            intent.putExtra("POST_OBJECT", model)
            holder.edit.context.startActivity(intent)
        }

        holder.delete.setOnClickListener{
            val dialog = AlertDialog.Builder(holder.delete.context)
            dialog.setTitle("Hapus")
            dialog.setMessage("Apakah Anda yakin?")
            dialog.setPositiveButton("Ya") { dialog: DialogInterface?, which: Int ->
                fStore.collection("posts").document(model.uid).delete()
                    .addOnSuccessListener {
                        Toast.makeText(holder.delete.context, "Post telah dihapus",
                            Toast.LENGTH_SHORT).show()
                    }
            }
            dialog.setNegativeButton("Tidak") { dialog: DialogInterface?, which: Int -> }
            dialog.show()
        }
    }
}