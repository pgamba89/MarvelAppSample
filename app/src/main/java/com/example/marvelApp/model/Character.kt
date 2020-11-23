package com.example.marvelApp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Character(
        val id: String? = null,
        val name: String? = null,
        val description: String? = null,
        val modified: String? = null,
        val resourceURI: String? = null,
        val urls: List<URL>? = null,
        val thumbnail: Thumbnail? = null,
        val comics: Comics? = null,
        val stories: Stories? = null,
        val events: Comics? = null,
        val series: Comics? = null
    ) : Parcelable