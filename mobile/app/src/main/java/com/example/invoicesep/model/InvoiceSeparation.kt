package com.example.invoicesep.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
// import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
class InvoiceSeparation(val invoice: Int, val users: List<String>) : Parcelable