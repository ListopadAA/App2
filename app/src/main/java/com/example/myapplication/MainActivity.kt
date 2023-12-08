package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.feature.aks.AksFragment
import com.example.myapplication.feature.cases.CasesFragment
import com.example.myapplication.feature.partners.PartnersFragment
import com.example.myapplication.feature.marketplace.MarketPlaceFragment
import com.example.myapplication.feature.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.Exception
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.core.doOnStarted
import com.example.myapplication.core.update
import com.example.myapplication.databinding.LoginFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {

    lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNav = findViewById(R.id.bottomNav) as BottomNavigationView
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.aks -> {
                    Fragment().findNavController().navigate(R.id.aksFragment)
                    true
                }
                R.id.cases -> {
                    Fragment().findNavController().navigate(R.id.casesFragment)
                    true
                }
                R.id.partners -> {
                    Fragment().findNavController().navigate(R.id.partnersFragment)
                    true
                }
                R.id.marketplace -> {
                    Fragment().findNavController().navigate(R.id.marketplaceFragment)
                    true
                }
                R.id.profile -> {
                    Fragment().findNavController().navigate(R.id.profileFragment)
                    true
                }

                else -> {true}
            }
        }
    }


}