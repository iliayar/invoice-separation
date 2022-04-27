package com.example.invoicesep

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.invoicesep.databinding.ActivityMainBinding.inflate
import com.example.invoicesep.databinding.ContactBinding
import com.example.invoicesep.model.User



class ContactAdapter(
    private var users: List<String>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    inner class ContactViewHolder(val binding: ContactBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: String) {
                binding.username.text = user
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemBinding =
            ContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ContactViewHolder(itemBinding)
        itemBinding.root.setOnClickListener {
            onClick(users[holder.bindingAdapterPosition])
        }
        return holder
    }

    public fun setData(newUsers: List<String>) {
        users = newUsers
        notifyItemRangeInserted(0, newUsers.size)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) = holder.bind(users[position])

    override fun getItemCount(): Int = users.size
}