package com.example.invoicesep

import android.util.Log
import android.util.Log.ERROR
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.Dispatcher
import retrofit2.Response

class LogicViewModel : ViewModel() {

    val state: MutableLiveData<State<Unit>> by lazy { MutableLiveData() }

    val contacts: MutableLiveData<State<List<String>>> by lazy { MutableLiveData() }

    val token = "f11559a3-2a45-4cdb-a0d7-e9042b7e447a"

    fun <T> apiFun(
        responseSupplier: suspend () -> Response<T>,
        onSuccess: (Response<T>) -> Unit = {},
        onFailure: (Response<T>) -> Unit = {},
    ) {
        viewModelScope.launch {
            try {
                val response = responseSupplier()
                if (response.isSuccessful) {
                    onSuccess(response)
                    state.value = Success()
                } else {
                    onFailure(response)
                    state.value = Failure(response.code())
                }
            } catch (t: Throwable) {
                Log.e("apiFun", t.message ?: "")
            }
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    fun fetchContacts() {
        apiFun(
            { ApiApp.instance.jsonPlaceHolderApi.getContacts(token) },
            {
                contacts.value = Success(it.body())
            },
            {
                contacts.value = Failure(it.code())
            })
    }

    @OptIn(ExperimentalSerializationApi::class)
    fun postContacts(users: List<String>) {
        apiFun({
            ApiApp.instance.jsonPlaceHolderApi.postContacts(
                token,
                users
            )
        })
    }

}