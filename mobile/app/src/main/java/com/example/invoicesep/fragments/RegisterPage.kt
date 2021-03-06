package com.example.invoicesep.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.invoicesep.*
import com.example.invoicesep.databinding.RegisterPageBinding
import com.example.invoicesep.model.UserLogin
import kotlinx.serialization.ExperimentalSerializationApi

class RegisterPage : Fragment(R.layout.register_page) {
    private lateinit var binding: RegisterPageBinding

    private val viewModel: LogicViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = RegisterPageBinding.bind(view)
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is Success -> {
                    viewModel.state.value = Loading()
                    makeToast("Successfully registered you. Please enter your data once again")
                    val action = RegisterPageDirections.actionRegisterPageToEnterPage()
                    findNavController().navigate(action)
                }
                is Failure -> makeToast("error occurred, response code: ${it.error}")
            }
        }
        register()
    }

    private fun register() {
        binding.registerButton.setOnClickListener {
            val login = binding.registerLogin.text.toString()
            val password = binding.registerPassword.text.toString()
            registerUser(login, password)
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    private fun registerUser(login: String, password: String) {
        val userLogin = UserLogin(login, password)
        viewModel.apiFun(
            {
                ApiApp.instance.jsonPlaceHolderApi.registerUser(userLogin)
            },
            onSuccess = {
                viewModel.token = it.body()?.string() ?: "token"
            }
        )
    }

    private fun makeToast(string: String) {
        Toast.makeText(
            context,
            string,
            Toast.LENGTH_SHORT
        ).show()
    }
}