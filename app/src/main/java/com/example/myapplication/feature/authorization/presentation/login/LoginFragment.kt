package com.example.myapplication.feature.authorization.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.core.doOnStarted
import com.example.myapplication.core.update
import com.example.myapplication.databinding.LoginFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.collectLatest

class LoginFragment : Fragment() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private lateinit var binding: LoginFragmentBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        hideBottomBar()
        viewLifecycleOwner.doOnStarted {
            viewModel.state.collectLatest {
                updateUi(it)
            }
        }
        viewLifecycleOwner.doOnStarted {
            viewModel.errorEvent.collectLatest {
                handleEvent(it)
            }
        }

        if (isUserAuthorized()) {
            findNavController().navigate(R.id.profileFragment)
        }
    }

    private fun isUserAuthorized(): Boolean {
        return auth.currentUser != null
    }

    private fun initViews() = with(binding) {
        etLogin.doAfterTextChanged {
            viewModel.onLoginChanged(it.toString())
        }
        etLoginPassword.doAfterTextChanged {
            viewModel.onPasswordChanged(it.toString())
        }
        btnLogin.setOnClickListener {
            viewModel.onLoginClicked()
        }
        btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.signUpFragment)
        }
    }

    private fun updateUi(loginState: LoginState) = with(binding) {
        etLogin.update(loginState.login)
        etLoginPassword.update(loginState.password)
    }

    private fun handleEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.LogIn -> {
                auth.signInWithEmailAndPassword(event.login, event.password)
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            findNavController().navigate(R.id.profileFragment)
                        } else {
                            Toast.makeText(
                                requireContext(),
                                "Authentication failed.",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }
            }

        }
    }

    private fun hideBottomBar() {
        val view = requireActivity().findViewById<View>(android.R.id.content)
        view.findViewById<View>(R.id.bottom_navigation)?.isVisible = false
    }
}
