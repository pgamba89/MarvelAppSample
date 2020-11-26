package com.example.marvelApp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Character(
        val id: String,
        val name: String? = null,
        val description: String? = null,
        val modified: String? = null,
        val resourceURI: String? = null,
        val thumbnail: Thumbnail? = null,
        val comics: Comics? = null,
        val stories: Stories? = null,
        val events: Comics? = null,
        val series: Comics? = null
    ) : Parcelable