package com.example.myapplication.feature.marketplace

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MarketPlaceFragment: Fragment() {
    lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatActivity().setContentView(R.layout.marketplace_fragment)

        bottomNav = AppCompatActivity().findViewById(R.id.bottomNav) as BottomNavigationView
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.aks -> {
                    findNavController().navigate(R.id.aksFragment)
                    true
                }
                R.id.cases -> {
                    findNavController().navigate(R.id.casesFragment)
                    true
                }
                R.id.partners -> {
                    findNavController().navigate(R.id.partnersFragment)
                    true
                }
                R.id.marketplace -> {
                    findNavController().navigate(R.id.marketplaceFragment)
                    true
                }
                R.id.profile -> {
                    findNavController().navigate(R.id.profileFragment)
                    true
                }

                else -> {true}
            }
        }
    }

}