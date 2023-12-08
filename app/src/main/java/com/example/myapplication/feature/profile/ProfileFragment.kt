package com.example.myapplication.feature.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.CasesFragmentBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth



class ProfileFragment : Fragment() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatActivity().setContentView(R.layout.profile_fragment)

        val name: TextView = AppCompatActivity().findViewById(R.id.name) as TextView

        val exbtn = AppCompatActivity().findViewById<ImageButton>(R.id.exitBtn)
        exbtn.setOnClickListener {
            auth.signOut()
            requireContext().startActivity(Intent(requireContext(), MainActivity::class.java))
        }
        val redbtn = AppCompatActivity().findViewById<ImageButton>(R.id.redbtn)
        redbtn.setOnClickListener {
            findNavController().navigate(R.id.redactFragment)
        }
    }
}
