package com.example.invoicesep.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.invoicesep.*
import com.example.invoicesep.databinding.EnterPageBinding
import com.example.invoicesep.model.UserLogin
import kotlinx.serialization.ExperimentalSerializationApi

class EnterPage : Fragment(R.layout.enter_page) {
    private lateinit var binding: EnterPageBinding

    private val viewModel: LogicViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = EnterPageBinding.bind(view)

        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is Success -> {
                    viewModel.name = binding.enterLogin.text.toString()
                    makeToast("success")
                    val action = EnterPageDirections.actionEnterPageToMainPage()
                    findNavController().navigate(action)
                }
                is Failure -> makeToast("error occurred, response code: ${it.error}")
            }
        }
        enter()
        register()
    }

    private fun enter() {
        binding.enter.setOnClickListener {
            loginUser(binding.enterLogin.text.toString(), binding.enterPassword.text.toString())
        }
    }

    private fun register() {
        binding.register.setOnClickListener {
            val action = EnterPageDirections.actionEnterPageToRegisterPage()
            findNavController().navigate(action)
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    private fun loginUser(login: String, password: String) {
        val userLogin = UserLogin(login, password)
        viewModel.apiFun(
            {
                ApiApp.instance.jsonPlaceHolderApi.loginUser(userLogin)
            },
            onSuccess = {
                viewModel.token = it.body()?.string() ?: "token"
            })
    }


    private fun makeToast(string: String) {
        Toast.makeText(
            context,
            string,
            Toast.LENGTH_SHORT
        ).show()
    }
}