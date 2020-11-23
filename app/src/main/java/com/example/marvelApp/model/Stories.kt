package com.example.marvelApp.model

data class Stories(
        val available: String? = null,
        val returned: String? = null,
        val collectionURI: String? = null,
        val items: List<StoriesItem>? = null
    )