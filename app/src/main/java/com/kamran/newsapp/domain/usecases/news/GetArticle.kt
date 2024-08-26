package com.kamran.newsapp.domain.usecases.news

import com.kamran.newsapp.data.local.NewsDao
import com.kamran.newsapp.domain.model.Article
import com.kamran.newsapp.domain.repository.NewsRepository

class GetArticle(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(url : String) : Article?{
        return newsRepository.getArticle(url)
    }

}