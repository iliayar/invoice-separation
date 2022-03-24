package com.example.invoicesep.api

import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
class InvoiceSeparation(val invoice: Int, val users: List<String>)