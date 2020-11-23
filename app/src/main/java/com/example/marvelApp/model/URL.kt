package com.example.marvelApp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class URL(
        val type: String? = null,
        val url: String? = null
    ) : Parcelable