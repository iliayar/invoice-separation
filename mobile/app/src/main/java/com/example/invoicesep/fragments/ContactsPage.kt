package com.example.invoicesep.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.invoicesep.*
import com.example.invoicesep.databinding.ContactsPageBinding
import com.example.invoicesep.databinding.MainPageBinding

class ContactsPage : Fragment(R.layout.contacts_page) {

    private lateinit var binding: ContactsPageBinding
    private val viewModel: LogicViewModel by activityViewModels()

    private val adapter: ContactAdapter = ContactAdapter(emptyList()) {
        Toast.makeText(
            this.context,
            "Clicked on ${it}",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ContactsPageBinding.bind(view)

        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView.adapter = adapter

        viewModel.contacts.observe(viewLifecycleOwner) {
            when (it) {
                is Success -> adapter.setData(it.value ?: emptyList())
                is Failure -> {
                    makeToast("Can't load contacts")
                    val action = ContactsPageDirections.actionContactsPageToMainPage()
                    findNavController().navigate(action)
                }
            }
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