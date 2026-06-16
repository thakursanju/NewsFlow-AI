package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.NewsFlowApplication

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            val app = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as NewsFlowApplication)
            HeadlinesViewModel(
                newsRepository = app.container.newsRepository,
                userPreferencesRepository = app.container.userPreferencesRepository
            )
        }
        initializer {
            val app = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as NewsFlowApplication)
            SearchViewModel(app.container.newsRepository)
        }
        initializer {
            val app = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as NewsFlowApplication)
            FavoritesViewModel(app.container.newsRepository)
        }
        initializer {
            val app = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as NewsFlowApplication)
            SettingsViewModel(app.container.userPreferencesRepository)
        }
        initializer {
            SharedArticleViewModel()
        }
    }
}
