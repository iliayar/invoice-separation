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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }


//    private fun registerUser(login: String, password: String) {
//        val userLogin = UserLogin(login, password)
//        apiFun({ ApiApp.instance.jsonPlaceHolderApi.registerUser(token, userLogin) })
//    }
//


//    private fun invoiceSeparation(invoice: Int, usersList: List<String>) {
//        val invoiceSeparation = InvoiceSeparation(invoice, usersList)
//        apiFun({ ApiApp.instance.jsonPlaceHolderApi.invoiceSeparation(token, invoiceSeparation) })
//    }
//
//    private fun postDebt(userName: String) {
//        val user = User(userName)
//        apiFun({
//            ApiApp.instance.jsonPlaceHolderApi.postDebt(token, user)
//        })
//    }
//
//    private fun getDebt(user: String) {
//        apiFun(
//            { ApiApp.instance.jsonPlaceHolderApi.getDebt(token, user) },
//            { makeToast(it.body().toString()) })
//    }
//


}
