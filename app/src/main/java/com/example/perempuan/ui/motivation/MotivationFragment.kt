package com.example.perempuan.ui.motivation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.perempuan.activity.post.PostCreateActivity
import com.example.perempuan.adapter.MotivationAdapter
import com.example.perempuan.databinding.FragmentMotivationBinding
import com.example.perempuan.model.Post
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class MotivationFragment : Fragment() {

    private lateinit var motivationViewModel: MotivationViewModel
    private var _binding: FragmentMotivationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val fStore = FirebaseFirestore.getInstance()
    private val reference: Query = fStore.collection("posts").whereEqualTo("category", "Motivasi")
    private var adapter: MotivationAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        motivationViewModel =
            ViewModelProvider(this).get(MotivationViewModel::class.java)

        _binding = FragmentMotivationBinding.inflate(inflater, container, false)
        val root: View = binding.root
        adapter = MotivationAdapter(getMotivation())
        binding.rvMotivation.layoutManager = LinearLayoutManager(root.context)
        binding.rvMotivation.adapter = adapter
        return root
    }

    override fun onStart() {
        super.onStart()
        adapter?.startListening()
    }

    private fun getMotivation(): FirestoreRecyclerOptions<Post> {
        return FirestoreRecyclerOptions.Builder<Post>()
            .setQuery(reference, Post::class.java)
            .build();
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        adapter?.stopListening()
    }
}