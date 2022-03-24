package com.example.invoicesep.api

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class UserLogin(val login: String, val password: String) : Parcelable