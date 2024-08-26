package com.kamran.newsapp.presentation.search

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.kamran.newsapp.domain.model.Article
import com.kamran.newsapp.presentation.common.ArticlesList
import com.kamran.newsapp.presentation.common.SearchBar
import com.kamran.newsapp.presentation.navgraph.Route
import com.kamran.newsapp.utils.Constants

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetails: (Article) -> Unit
) {
    Column(
        modifier = modifier
            .padding(
                32.dp, 32.dp, 32.dp, 0.dp
            )
            .statusBarsPadding()
    ) {
        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = { event(SearchEvent.UpdateSearchQuery(it))},
            onSearch = { event(SearchEvent.SearchNews)}
        )

        Spacer(modifier = Modifier.height(16.dp))

        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            Log.d(Constants.TAG, articles.itemCount.toString() )
            ArticlesList(articles = articles, onClick = { article ->  navigateToDetails(article)})
        }
    }

}