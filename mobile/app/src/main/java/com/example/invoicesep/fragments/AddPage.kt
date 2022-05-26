package com.example.invoicesep.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.invoicesep.ApiApp
import com.example.invoicesep.LogicViewModel
import com.example.invoicesep.R
import com.example.invoicesep.databinding.AddContactsBinding
import kotlinx.serialization.ExperimentalSerializationApi

class AddPage : Fragment(R.layout.add_contacts) {
    private lateinit var binding: AddContactsBinding
    private val viewModel: LogicViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AddContactsBinding.bind(view)
        addContact()
        getBack()
    }

    private fun addContact() {
        binding.addContact.setOnClickListener {
            postContacts()
        }
    }

    private fun getBack() {
        binding.back.setOnClickListener {
            val action = AddPageDirections.actionAddPageToMainPage()
            findNavController().navigate(action)
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    fun postContacts() {
        viewModel.apiFun(
            responseSupplier = {
                ApiApp.instance.jsonPlaceHolderApi.postContacts(
                    viewModel.token,
                    listOf(binding.enterName.text.toString())
                )
            },
            onSuccess = {
                makeToast("Contact ${binding.enterName.text.toString()} successfully added")
                binding.enterName.text.clear()
            },
            onFailure = {
                makeToast("Can't add contact, response code ${it.code()}")
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