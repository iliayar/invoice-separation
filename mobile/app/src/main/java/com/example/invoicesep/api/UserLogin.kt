package com.example.invoicesep.api

import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class UserLogin(val login: String, val password: String)