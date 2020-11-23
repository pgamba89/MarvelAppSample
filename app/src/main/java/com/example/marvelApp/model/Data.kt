package com.example.marvelApp.model

data class Data(
        val offset: String? = null,
        val limit: String? = null,
        val total: String? = null,
        val count: String? = null,
        val results: List<Character>? = null
    )