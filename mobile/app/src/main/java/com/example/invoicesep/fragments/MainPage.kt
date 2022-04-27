package com.example.invoicesep.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.invoicesep.ApiApp
import com.example.invoicesep.ContactAdapter
import com.example.invoicesep.LogicViewModel
import com.example.invoicesep.R
import com.example.invoicesep.databinding.MainPageBinding

class MainPage: Fragment(R.layout.main_page) {

    private lateinit var binding: MainPageBinding
    private val viewModel: LogicViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MainPageBinding.bind(view)

//        binding.add.setOnClickListener { postDebt("string") }
        binding.viewContacts.setOnClickListener { getContacts() }
        binding.Name.setOnClickListener { }
    }

    private fun getContacts() {
        viewModel.fetchContacts()
        val action = MainPageDirections.actionMainPageToContactsPage()
        findNavController().navigate(action)
    }


    private fun postContacts(users: List<String>) {
        viewModel.postContacts(users)
    }

}