package com.example.invoicesep.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
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
            when(it) {
                is Success -> {
//                    viewModel.token = it.value!!
                    makeToast("success")
                    val action = RegisterPageDirections.actionRegisterPageToMainPage()
                    findNavController().navigate(action)
                }
                is Failure -> makeToast("error occurred, response code: ${it.error}")
            }
        }

        enter()
    }

    private fun enter() {
        binding.enter.setOnClickListener {
            loginUser(binding.enterLogin.text.toString(), binding.enterPassword.text.toString())
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    private fun loginUser(login: String, password: String) {
        val userLogin = UserLogin(login, password)
        viewModel.apiFun({ ApiApp.instance.jsonPlaceHolderApi.loginUser(viewModel.token, userLogin) })
    }


    private fun makeToast(string: String) {
        Toast.makeText(
            context,
            string,
            Toast.LENGTH_SHORT
        ).show()
    }
}