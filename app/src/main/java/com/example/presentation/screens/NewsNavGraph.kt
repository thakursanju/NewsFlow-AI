package com.example.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.example.presentation.viewmodel.*

sealed class Screen(val route: String, val title: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    object Splash : Screen("splash", "Splash", Icons.Filled.Home)
    object Onboarding : Screen("onboarding", "Onboarding", Icons.Filled.Home)
    object Auth : Screen("auth", "Auth", Icons.Filled.Person)
    object Headlines : Screen("headlines", "Headlines", Icons.Filled.Home)
    object Search : Screen("search", "Search", Icons.Filled.Search)
    object Favorites : Screen("favorites", "Favorites", Icons.Filled.Favorite)
    object Settings : Screen("settings", "Settings", Icons.Filled.Settings)
}

@Composable
fun NewsNavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val sharedViewModel: SharedArticleViewModel = viewModel(factory = AppViewModelProvider.Factory)
    
    val items = listOf(Screen.Headlines, Screen.Search, Screen.Favorites, Screen.Settings)
    
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    
    val showBottomBar = currentDestination?.route in items.map { it.route }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                ) {
                    items.forEach { screen ->
                        NavigationBarItem(
                            icon = { Icon(screen.icon, contentDescription = screen.title) },
                            label = { Text(screen.title, style = MaterialTheme.typography.labelMedium) },
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                indicatorColor = MaterialTheme.colorScheme.primaryContainer
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = modifier.padding(innerPadding).fillMaxSize()
        ) {
            composable(Screen.Splash.route) {
                SplashScreen(onTimeout = {
                    navController.navigate(Screen.Onboarding.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                })
            }
            composable(Screen.Onboarding.route) {
                OnboardingScreen(onFinish = {
                    navController.navigate(Screen.Auth.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                })
            }
            composable(Screen.Auth.route) {
                AuthScreen(onAuthSuccess = {
                    navController.navigate(Screen.Headlines.route) {
                        popUpTo(Screen.Auth.route) { inclusive = true }
                    }
                })
            }
            composable(Screen.Headlines.route) {
                HeadlinesScreen(
                    viewModel = viewModel(factory = AppViewModelProvider.Factory),
                    onArticleClick = { article ->
                        sharedViewModel.selectArticle(article)
                        navController.navigate("article_details")
                    }
                )
            }
            composable(Screen.Search.route) {
                SearchScreen(
                    viewModel = viewModel(factory = AppViewModelProvider.Factory),
                    onArticleClick = { article ->
                        sharedViewModel.selectArticle(article)
                        navController.navigate("article_details")
                    }
                )
            }
            composable(Screen.Favorites.route) {
                FavoritesScreen(
                    viewModel = viewModel(factory = AppViewModelProvider.Factory),
                    onArticleClick = { article ->
                        sharedViewModel.selectArticle(article)
                        navController.navigate("article_details")
                    }
                )
            }
            composable(Screen.Settings.route) {
                SettingsScreen(
                    viewModel = viewModel(factory = AppViewModelProvider.Factory)
                )
            }
            composable("article_details") {
                val article by sharedViewModel.selectedArticle.collectAsState()
                article?.let {
                    ArticleDetailsScreen(
                        article = it,
                        onBack = { navController.popBackStack() },
                        viewModel = viewModel(factory = AppViewModelProvider.Factory)
                    )
                } ?: run {
                    navController.popBackStack()
                }
            }
        }
    }
}
