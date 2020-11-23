package com.example.marvelApp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ComicsItem(
        val resourceURI: String? = null,
        val name: String? = null
    ) : Parcelable