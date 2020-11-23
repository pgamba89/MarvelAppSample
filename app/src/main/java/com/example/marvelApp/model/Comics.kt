package com.example.marvelApp.model

data class Comics(
        val available: String? = null,
        val returned: String? = null,
        val collectionURI: String? = null,
        val items: List<ComicsItem>? = null
    )