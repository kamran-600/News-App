package com.kamran.newsapp.presentation.bookmark

import com.kamran.newsapp.domain.model.Article

data class BookmarkState(val articles : List<Article> = emptyList())