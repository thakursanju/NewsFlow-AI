package com.example.data.mapper

import com.example.data.local.ArticleEntity
import com.example.data.model.ArticleDto
import com.example.domain.model.Article

fun ArticleDto.toDomainModel(): Article {
    return Article(
        author = author ?: "",
        title = title ?: "",
        description = description ?: "",
        url = url ?: "",
        urlToImage = urlToImage ?: "",
        sourceName = source?.name ?: "",
        publishedAt = publishedAt ?: "",
        content = content ?: ""
    )
}

fun ArticleEntity.toDomainModel(): Article {
    return Article(
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        sourceName = sourceName,
        publishedAt = publishedAt,
        content = content,
        isSaved = true
    )
}

fun Article.toEntity(): ArticleEntity {
    return ArticleEntity(
        url = url,
        author = author,
        title = title,
        description = description,
        urlToImage = urlToImage,
        sourceName = sourceName,
        publishedAt = publishedAt,
        content = content
    )
}
