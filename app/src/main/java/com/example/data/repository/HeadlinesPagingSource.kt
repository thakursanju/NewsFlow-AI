package com.example.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.mapper.toDomainModel
import com.example.data.remote.NewsApiService
import com.example.domain.model.Article
import retrofit2.HttpException
import java.io.IOException

class HeadlinesPagingSource(
    private val apiService: NewsApiService,
    private val country: String
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val position = params.key ?: 1
        return try {
            val response = apiService.getTopHeadlines(
                country = country,
                page = position,
                pageSize = 20
            )
            val articles = response.articles
                .filter { it.title != null && it.title != "[Removed]" && it.url != null }
                .map { it.toDomainModel() }

            LoadResult.Page(
                data = articles,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (articles.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
