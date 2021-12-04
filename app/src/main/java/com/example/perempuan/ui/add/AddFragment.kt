package com.example.perempuan.ui.add

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.perempuan.activity.post.PostCreateActivity
import com.example.perempuan.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private lateinit var addViewModel: AddViewModel
    private var _binding: FragmentAddBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addViewModel =
            ViewModelProvider(this).get(AddViewModel::class.java)

        _binding = FragmentAddBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onStart() {
        super.onStart()
        binding.buttonKisah.setOnClickListener{
            startActivity(Intent(requireContext(), PostCreateActivity::class.java))
        }

        binding.buttonMot.setOnClickListener{
            startActivity(Intent(requireContext(), PostCreateActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}