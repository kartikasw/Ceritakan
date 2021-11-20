package com.example.perempuan.ui.motivation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.perempuan.databinding.FragmentMotivationBinding

class MotivationFragment : Fragment() {

    private lateinit var motivationViewModel: MotivationViewModel
    private var _binding: FragmentMotivationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        motivationViewModel =
            ViewModelProvider(this).get(MotivationViewModel::class.java)

        _binding = FragmentMotivationBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.text
//        motivationViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}