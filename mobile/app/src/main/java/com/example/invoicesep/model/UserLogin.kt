package com.example.invoicesep.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
// import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class UserLogin(val login: String, val password: String) : Parcelable