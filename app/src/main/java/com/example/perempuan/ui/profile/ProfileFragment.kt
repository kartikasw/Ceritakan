package com.example.perempuan.ui.profile

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.appcompat.app.AlertDialog
import com.example.perempuan.activity.LandingActivity
import com.example.perempuan.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val fAuth = FirebaseAuth.getInstance()
    private val fStore = FirebaseFirestore.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = fAuth.currentUser
        fStore.collection("users").document(user!!.uid).get().addOnSuccessListener {
            tvUsername.setText(it.getString("username"))
            tvEmail.setText(it.getString("email"))
        }

        btnEdit.setOnClickListener{

        }

        btnLogout.setOnClickListener{
            val dialog = AlertDialog.Builder(requireContext())
            dialog.setTitle("Log out")
            dialog.setMessage("Apakah Anda yakin?")
            dialog.setPositiveButton("Ya") { dialog: DialogInterface?, which: Int ->
                fAuth.signOut()
                startActivity(Intent(requireContext(), LandingActivity::class.java))
            }
            dialog.setNegativeButton("Tidak") { dialog: DialogInterface?, which: Int -> }
            dialog.show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}