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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.perempuan.activity.LandingActivity
import com.example.perempuan.activity.SettingsActivity
import com.example.perempuan.activity.profile.ProfileEditActivity
import com.example.perempuan.adapter.ProfileAdapter
import com.example.perempuan.databinding.FragmentProfileBinding
import com.example.perempuan.model.Post
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var username: String

    private val fAuth = FirebaseAuth.getInstance()
    val user = fAuth.currentUser
    private val fStore = FirebaseFirestore.getInstance()
    private val reference: Query = fStore.collection("posts").whereEqualTo("user_uid", user!!.uid)
    private var adapter: ProfileAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        adapter = ProfileAdapter(getPost())
        binding.rvPost.layoutManager = LinearLayoutManager(root.context)
        binding.rvPost.adapter = adapter
        return root
    }


    override fun onStart() {
        super.onStart()

        fStore.collection("users").document(user!!.uid).get().addOnSuccessListener {
            binding.tvUsername.setText(it.getString("username"))
            binding.tvEmail.setText(it.getString("email"))
        }

        binding.btnEdit.setOnClickListener{
            var intent = Intent(getActivity(), ProfileEditActivity::class.java)
            startActivity(intent)
        }

        binding.btnSettings.setOnClickListener{
            var intent = Intent(getActivity(), SettingsActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener{
            val dialog = AlertDialog.Builder(requireContext())
            dialog.setTitle("Log out")
            dialog.setMessage("Apakah Anda yakin?")
            dialog.setPositiveButton("Ya") { dialog: DialogInterface?, which: Int ->
                fAuth.signOut()
                startActivity(Intent(requireContext(), LandingActivity::class.java))
                activity?.finish()
            }
            dialog.setNegativeButton("Tidak") { dialog: DialogInterface?, which: Int -> }
            dialog.show()
        }
        adapter?.startListening()
    }

    private fun getPost(): FirestoreRecyclerOptions<Post> {
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