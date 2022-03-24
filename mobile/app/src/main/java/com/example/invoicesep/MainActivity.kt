package com.example.invoicesep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.coroutineScope
import com.example.invoicesep.api.Debt
import com.example.invoicesep.api.InvoiceSeparation
import com.example.invoicesep.api.User
import com.example.invoicesep.api.UserLogin
import com.example.invoicesep.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import retrofit2.Response

@ExperimentalSerializationApi
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.add.setOnClickListener { postContacts(listOf("Fedos")) }
        binding.viewContacts.setOnClickListener { getContacts() }

    }

    private fun <T> apiFun(
        responseSupplier: suspend () -> Response<T>,
        onSuccess: (Response<T>) -> Unit = {},
        onFailure: (Response<T>) -> Unit = {}
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
        apiFun({ ApiApp.instance.jsonPlaceHolderApi.registerUser(userLogin) })
    }

    private fun loginUser(login: String, password: String) {
        val userLogin = UserLogin(login, password)
        apiFun({ ApiApp.instance.jsonPlaceHolderApi.loginUser(userLogin) })
    }

    private fun postContacts(usersList: List<String>) {
        val users = usersList.map { User(it) }
        apiFun({ ApiApp.instance.jsonPlaceHolderApi.postContacts(users) })
    }

    private fun getContacts() {
        apiFun(
            { ApiApp.instance.jsonPlaceHolderApi.getContacts() },
            { makeToast(it.body().toString()) })
    }

    private fun invoiceSeparation(invoice: Int, usersList: List<String>) {
        val invoiceSeparation = InvoiceSeparation(invoice, usersList)
        apiFun({ ApiApp.instance.jsonPlaceHolderApi.invoiceSeparation(invoiceSeparation) })
    }

    private fun postDebt(value: Int) {
        val debt = Debt(value)
        apiFun({ ApiApp.instance.jsonPlaceHolderApi.postDebt(debt) })
    }

    private fun getDebt(userInfo: String) {
        val user = User(userInfo)
        apiFun(
            { ApiApp.instance.jsonPlaceHolderApi.getDebt(user) },
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