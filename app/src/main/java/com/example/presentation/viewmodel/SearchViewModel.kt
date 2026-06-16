package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.model.Article
import com.example.domain.repository.NewsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val searchResults: Flow<PagingData<Article>> = _searchQuery
        .debounce(500)
        .filter { it.isNotBlank() }
        .flatMapLatest { query ->
            newsRepository.searchNews(query).cachedIn(viewModelScope)
        }
        .cachedIn(viewModelScope)

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun saveArticle(article: Article) {
        viewModelScope.launch {
            newsRepository.saveArticle(article)
        }
    }
    
    fun clearSearch() {
        _searchQuery.value = ""
    }
}
