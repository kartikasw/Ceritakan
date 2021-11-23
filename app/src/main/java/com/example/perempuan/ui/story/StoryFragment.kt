package com.example.perempuan.ui.story

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.perempuan.activity.story.StoryCreateActivity
import com.example.perempuan.adapter.StoryAdapter
import com.example.perempuan.databinding.FragmentStoryBinding
import com.example.perempuan.model.Story
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_story.*

class StoryFragment : Fragment() {

    private lateinit var storyViewModel: StoryViewModel
    private var _binding: FragmentStoryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val fStore = FirebaseFirestore.getInstance()
    private val reference: Query = fStore.collection("stories").whereEqualTo("category", "Kisah")
    private var adapter: StoryAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        storyViewModel =
            ViewModelProvider(this).get(StoryViewModel::class.java)

        _binding = FragmentStoryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        adapter = StoryAdapter(getStory())
        binding.rvStory.layoutManager = LinearLayoutManager(root.context)
        binding.rvStory.adapter = adapter
        return root
    }

    override fun onStart() {
        super.onStart()
        btnCreate.setOnClickListener{
            startActivity(Intent(requireContext(), StoryCreateActivity::class.java))
        }
        adapter?.startListening()
    }

    private fun getStory(): FirestoreRecyclerOptions<Story> {
        return FirestoreRecyclerOptions.Builder<Story>()
            .setQuery(reference, Story::class.java)
            .build();
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        adapter?.stopListening()
    }
}