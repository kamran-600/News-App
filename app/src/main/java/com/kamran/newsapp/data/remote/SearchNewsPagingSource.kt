package com.kamran.newsapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kamran.newsapp.data.remote.dto.NewsResponse
import com.kamran.newsapp.domain.model.Article

class SearchNewsPagingSource(
    private val newsApi: NewsApi,
    private val searchQuery: String,
    private val sources: String
    ) : PagingSource<Int, Article>() {
    private var totalNewsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1

        return try {
            val newsResponse = newsApi.getNews(page, sources)
            totalNewsCount += newsResponse.articles.size
            val articles = newsResponse.articles.distinctBy { it.title }

            LoadResult.Page(
                data = articles,
                nextKey = if (totalNewsCount == newsResponse.totalResults) null else page + 1,
                prevKey = if (page == 1) null else page - 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }


    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    }
}