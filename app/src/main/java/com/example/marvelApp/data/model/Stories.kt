package com.example.marvelApp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Stories(
        val available: String? = null,
        val returned: String? = null,
        val collectionURI: String? = null,
        val items: List<StoriesItem>? = null
    ) : Parcelable

@Parcelize
data class StoriesItem(
    val resourceURI: String? = null,
    val name: String? = null,
    val type: String? = null
) : Parcelable