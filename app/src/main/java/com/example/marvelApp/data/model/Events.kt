package com.example.marvelApp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Events(
    val available: String = "",
    val collectionURI: String = "",
    val items: ArrayList<Item>? = null,
    val returned: String = ""
) : Parcelable