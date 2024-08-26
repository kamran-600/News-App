package com.kamran.newsapp.utils

sealed class Screen {
    data object Register : Screen()
    data object Login : Screen()
    data object Home : Screen()
}