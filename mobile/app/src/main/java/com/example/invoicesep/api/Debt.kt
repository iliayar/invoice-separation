package com.example.invoicesep.api

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
class Debt(val value: Int) : Parcelable