package com.kamran.newsapp.data.remote.dto

import com.kamran.newsapp.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)