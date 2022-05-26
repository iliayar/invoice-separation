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

    val state: MutableLiveData<State> by lazy { MutableLiveData() }
    var contacts: MutableList<String> = mutableListOf()
    var token: String = "token"
    var name: String = "name"
    var debt: Int = 0

    fun <T> apiFun(
        responseSupplier: suspend () -> Response<T>,
        onSuccess: (Response<T>) -> Unit = {},
        onFailure: (Response<T>) -> Unit = {},
    ) {
        viewModelScope.launch {
            try {
                state.value = Loading()
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
}