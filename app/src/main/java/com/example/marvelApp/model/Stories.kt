package com.example.marvelApp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Stories(
        val available: String? = null,
        val returned: String? = null,
        val collectionURI: String? = null,
        val items: List<StoriesItem>? = null
    ) : Parcelable