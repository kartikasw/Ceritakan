package com.example.perempuan.ui.story

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.perempuan.activity.story.StoryCreateActivity
import com.example.perempuan.databinding.FragmentStoryBinding

class StoryFragment : Fragment() {

    private lateinit var storyViewModel: StoryViewModel
    private var _binding: FragmentStoryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        storyViewModel =
            ViewModelProvider(this).get(StoryViewModel::class.java)

        _binding = FragmentStoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnCreate.setOnClickListener{
                startActivity(Intent(requireContext(), StoryCreateActivity::class.java))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}