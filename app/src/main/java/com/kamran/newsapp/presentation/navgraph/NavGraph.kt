package com.kamran.newsapp.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.kamran.newsapp.presentation.news_navigator.NewsNavigator
import com.kamran.newsapp.presentation.onBoarding.OnBoardingScreen
import com.kamran.newsapp.presentation.onBoarding.OnBoardingViewModel

@Composable
fun NavGraph(
    startDestination: String, modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(route = Route.OnBoardingScreen.route) {
                val viewmodel = hiltViewModel<OnBoardingViewModel>()

                OnBoardingScreen(event = viewmodel::onEvent)
            }
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ) {
            composable(route = Route.NewsNavigatorScreen.route) {
                NewsNavigator()
            }
        }
    }
}