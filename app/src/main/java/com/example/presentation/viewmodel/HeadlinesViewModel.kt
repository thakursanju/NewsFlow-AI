package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.data.local.UserPreferencesRepository
import com.example.domain.model.Article
import com.example.domain.repository.NewsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HeadlinesViewModel(
    private val newsRepository: NewsRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    val country = userPreferencesRepository.country.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = "us"
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    val headlines: Flow<PagingData<Article>> = country.flatMapLatest { selectedCountry ->
        newsRepository.getHeadlines(selectedCountry).cachedIn(viewModelScope)
    }

    fun saveArticle(article: Article) {
        viewModelScope.launch {
            newsRepository.saveArticle(article)
        }
    }
}
