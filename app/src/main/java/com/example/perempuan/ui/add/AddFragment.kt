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

    private var _binding: FragmentAddBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onStart() {
        super.onStart()
        binding.buttonKisah.setOnClickListener{
            val intent = Intent(requireContext(), PostCreateActivity::class.java)
            intent.putExtra("CATEGORY_STRING", "kisah")
            startActivity(intent)
        }

        binding.buttonMot.setOnClickListener{
            val intent = Intent(requireContext(), PostCreateActivity::class.java)
            intent.putExtra("CATEGORY_STRING", "motivasi")
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}