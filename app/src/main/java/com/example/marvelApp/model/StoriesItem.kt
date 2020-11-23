package com.example.marvelApp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StoriesItem(
        val resourceURI: String? = null,
        val name: String? = null,
        val type: String? = null
) : Parcelable