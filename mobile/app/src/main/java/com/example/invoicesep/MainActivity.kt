package com.example.invoicesep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.invoicesep.databinding.ActivityMainBinding
import com.example.invoicesep.model.InvoiceSeparation
import com.example.invoicesep.model.User
import com.example.invoicesep.model.UserLogin
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import retrofit2.Response

@ExperimentalSerializationApi
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    val token = "eb9701a3-dd69-4052-8b05-d3391764ec50"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.add.setOnClickListener { postDebt("string") }
        binding.viewContacts.setOnClickListener { getContacts() }
        binding.Name.setOnClickListener { }
    }

    private fun <T> apiFun(
        responseSupplier: suspend () -> Response<T>,
        onSuccess: (Response<T>) -> Unit = {},
        onFailure: (Response<T>) -> Unit = {},
    ) {
        lifecycle.coroutineScope.launch {
            try {
                val response = responseSupplier()
                if (response.isSuccessful) {
                    makeToast("success")
                    onSuccess(response)
                } else {
                    makeToast("error occurred, response code: ${response.code()}")
                    onFailure(response)
                }
            } catch (t: Throwable) {
                makeToast(t.message.toString())
            }
        }
    }

    private fun registerUser(login: String, password: String) {
        val userLogin = UserLogin(login, password)
        apiFun({ ApiApp.instance.jsonPlaceHolderApi.registerUser(token, userLogin) })
    }

    private fun loginUser(login: String, password: String) {
        val userLogin = UserLogin(login, password)
        apiFun({ ApiApp.instance.jsonPlaceHolderApi.loginUser(token, userLogin) })
    }

    private fun postContacts(users: List<String>) {
        apiFun({
            ApiApp.instance.jsonPlaceHolderApi.postContacts(
                token,
                users
            )
        })
    }

    private fun getContacts() {
        apiFun(
            { ApiApp.instance.jsonPlaceHolderApi.getContacts(token) },
            {
                recyclerView = findViewById(R.id.recycler_view)
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = ContactAdapter(it.body()!!) {
                    Toast.makeText(
                        this,
                        "Clicked on ${it}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            },
            {
                Toast.makeText(
                    this,
                    "Can't get contacts!",
                    Toast.LENGTH_SHORT
                ).show()
            })
    }

    private fun invoiceSeparation(invoice: Int, usersList: List<String>) {
        val invoiceSeparation = InvoiceSeparation(invoice, usersList)
        apiFun({ ApiApp.instance.jsonPlaceHolderApi.invoiceSeparation(token, invoiceSeparation) })
    }

    private fun postDebt(userName: String) {
        val user = User(userName)
        apiFun({
            ApiApp.instance.jsonPlaceHolderApi.postDebt(token, user)
        })
    }

    private fun getDebt(user: String) {
        apiFun(
            { ApiApp.instance.jsonPlaceHolderApi.getDebt(token, user) },
            { makeToast(it.body().toString()) })
    }

    private fun makeToast(string: String) {
        Toast.makeText(
            this@MainActivity,
            string,
            Toast.LENGTH_SHORT
        ).show()
    }

}
