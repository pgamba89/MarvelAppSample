package com.example.marvelApp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Series(
    val available: String = "",
    val collectionURI: String = "",
    val items: ArrayList<Item>? = null,
    val returned: String = ""
) : Parcelable

@Parcelize
data class Item(
    val name: String = "",
    val resourceURI: String = "",
    val type: String = ""
) : Parcelable