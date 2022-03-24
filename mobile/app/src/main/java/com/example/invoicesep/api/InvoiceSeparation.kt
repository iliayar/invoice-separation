package com.example.invoicesep.api

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
class InvoiceSeparation(val invoice: Int, val users: List<String>) : Parcelable