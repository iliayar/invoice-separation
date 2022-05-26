package com.example.invoicesep.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.invoicesep.*
import com.example.invoicesep.databinding.MainPageBinding
import kotlinx.serialization.ExperimentalSerializationApi

class MainPage : Fragment(R.layout.main_page) {

    private lateinit var binding: MainPageBinding
    private val viewModel: LogicViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MainPageBinding.bind(view)
        binding.Name.text = viewModel.name
        binding.debt.text = viewModel.debt.toString()
        getContacts()
        postContacts()
        newMeeting()
        fetchContacts()
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is Success -> {
                    for (contact in viewModel.contacts) {
                        getDebt(contact)
                    }
                    binding.debt.text = viewModel.debt.toString()
                }
                is Failure -> {
                    makeToast("Can't fetch the contacts, response code ${it.error}")
                }
            }
            viewModel.state.value = Loading()
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    fun fetchContacts() {
        viewModel.apiFun(
            { ApiApp.instance.jsonPlaceHolderApi.getContacts(viewModel.token) },
            {
                viewModel.contacts = (it.body()!! as MutableList<String>)
            })
    }

    @OptIn(ExperimentalSerializationApi::class)
    private fun getDebt(name: String) {
        viewModel.apiFun(
            {
                ApiApp.instance.jsonPlaceHolderApi.getDebt(viewModel.token, name)
            },
            onSuccess = {
                viewModel.debt += it.body()!!
            },
            onFailure = {
                makeToast("Can't get the debt of ${name}, response code ${it.code()}")
            }
        )
    }

    private fun getContacts() {
        binding.viewContacts.setOnClickListener {
            val action = MainPageDirections.actionMainPageToContactsPage()
            findNavController().navigate(action)
        }
    }

    private fun postContacts() {
        binding.add.setOnClickListener {
            val action = MainPageDirections.actionMainPageToAddPage()
            findNavController().navigate(action)
        }
    }

    private fun newMeeting() {
        viewModel.state.value = Loading()
        binding.organisation.setOnClickListener {
            val action = MainPageDirections.actionMainPageToMeetingPage()
            findNavController().navigate(action)
        }
    }

    private fun makeToast(string: String) {
        Toast.makeText(
            context,
            string,
            Toast.LENGTH_SHORT
        ).show()
    }
}