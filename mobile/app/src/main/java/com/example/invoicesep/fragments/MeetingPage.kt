package com.example.invoicesep.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.invoicesep.*
import com.example.invoicesep.databinding.MeetingPageBinding
import com.example.invoicesep.model.InvoiceSeparation
import kotlinx.serialization.ExperimentalSerializationApi

class MeetingPage : Fragment(R.layout.meeting_page) {

    private lateinit var binding: MeetingPageBinding
    private val viewModel: LogicViewModel by activityViewModels()
    private val members : MutableList<String> = mutableListOf()
    private lateinit var adapter : ContactAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MeetingPageBinding.bind(view)
        adapter = ContactAdapter(members) {
            makeToast("$it is in the party")
        }
        binding.partyMembers.layoutManager = LinearLayoutManager(this.context)
        binding.partyMembers.adapter = adapter
        join()
        startParty()
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is Success -> {
                    makeToast("Lets Rock!")
                    val action = MeetingPageDirections.actionMeetingPageToMainPage()
                    findNavController().navigate(action)
                }
                is Failure -> {
                    makeToast("Something went wrong, can't organise the party")
                }
            }
        }
    }

    private fun join() {
        binding.add.setOnClickListener {
            val name = binding.name.text.toString()
            if (name in viewModel.contacts && name !in members) {
                members.add(name);
                adapter.notifyDataSetChanged()
                makeToast("$name just joined party")
                binding.name.text.clear()
            } else {
                makeToast("Can't add $name to the party")
            }
        }
    }

    private fun startParty() {
        binding.startMeeting.setOnClickListener {
            invoiceSeparation()
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    private fun invoiceSeparation() {
        viewModel.apiFun(
            responseSupplier = {
                val invoice: Int = binding.sum.text.toString().toInt()
                ApiApp.instance.jsonPlaceHolderApi.invoiceSeparation(viewModel.token, InvoiceSeparation(invoice, members))
            },
            onSuccess = {
                binding.sum.text.clear()
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