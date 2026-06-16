package com.example.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.local.NewsDao
import com.example.data.mapper.toDomainModel
import com.example.data.mapper.toEntity
import com.example.data.remote.NewsApiService
import com.example.domain.model.Article
import com.example.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsRepositoryImpl(
    private val apiService: NewsApiService,
    private val newsDao: NewsDao
) : NewsRepository {

    override fun getHeadlines(country: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { HeadlinesPagingSource(apiService, country) }
        ).flow
    }

    override fun searchNews(query: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SearchPagingSource(apiService, query) }
        ).flow
    }

    override fun getSavedArticles(): Flow<List<Article>> {
        return newsDao.getSavedArticles().map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    override suspend fun saveArticle(article: Article) {
        newsDao.saveArticle(article.toEntity())
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.deleteArticle(article.toEntity())
    }

    override fun isArticleSaved(url: String): Flow<Boolean> {
        return newsDao.isArticleSaved(url)
    }
}
