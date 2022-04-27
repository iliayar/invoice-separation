package com.example.invoicesep

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.invoicesep.model.User
import kotlinx.android.synthetic.main.contact.view.*


class ContactAdapter(
    private val users: List<String>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    inner class ContactViewHolder(val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(user: String) {
            with(root) {
                username.text = user
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val holder = ContactViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.contact, parent, false)
        )
        holder.root.setOnClickListener {
            onClick(users[holder.bindingAdapterPosition])
        }
        return holder
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) = holder.bind(users[position])

    override fun getItemCount(): Int = users.size
}