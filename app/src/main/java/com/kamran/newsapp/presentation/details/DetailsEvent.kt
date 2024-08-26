package com.kamran.newsapp.presentation.details

import com.kamran.newsapp.domain.model.Article

sealed class DetailsEvent {

    data class UpsertDeleteArticle(val article: Article): DetailsEvent()

    data object RemoveSideEffect : DetailsEvent()
}