package com.example.invoicesep.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.invoicesep.ContactAdapter
import com.example.invoicesep.LogicViewModel
import com.example.invoicesep.R
import com.example.invoicesep.databinding.ContactsPageBinding

class ContactsPage : Fragment(R.layout.contacts_page) {

    private lateinit var binding: ContactsPageBinding
    private val viewModel: LogicViewModel by activityViewModels()

    private val adapter: ContactAdapter = ContactAdapter(emptyList()) {
        makeToast("Clicked on $it")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ContactsPageBinding.bind(view)
        setData()
        setFab()
    }

    private fun setData() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView.adapter = adapter
        adapter.setData(viewModel.contacts)
    }

    private fun setFab() {
        binding.fab.setOnClickListener {
            val action = ContactsPageDirections.actionContactsPageToAddPage()
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