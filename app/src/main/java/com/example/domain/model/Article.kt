package com.example.domain.model

data class Article(
    val id: Int = 0,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val sourceName: String,
    val publishedAt: String,
    val content: String,
    val isSaved: Boolean = false
)
