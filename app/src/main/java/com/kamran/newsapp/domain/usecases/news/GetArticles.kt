package com.kamran.newsapp.domain.usecases.news

import com.kamran.newsapp.domain.model.Article
import com.kamran.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetArticles(private val newsRepository: NewsRepository) {

    operator fun invoke() : Flow<List<Article>> {
        return newsRepository.getArticles()
    }
}