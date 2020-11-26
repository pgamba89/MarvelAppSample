package com.example.marvelApp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Thumbnail(
        val path: String? = null,
        val extension: String? = null
    ) : Parcelable