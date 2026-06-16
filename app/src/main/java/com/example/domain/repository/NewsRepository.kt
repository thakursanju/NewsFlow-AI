package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getHeadlines(country: String): Flow<PagingData<Article>>
    fun searchNews(query: String): Flow<PagingData<Article>>
    fun getSavedArticles(): Flow<List<Article>>
    suspend fun saveArticle(article: Article)
    suspend fun deleteArticle(article: Article)
    fun isArticleSaved(url: String): Flow<Boolean>
}
