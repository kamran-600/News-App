package com.kamran.newsapp.presentation.onBoarding

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kamran.newsapp.presentation.common.NewsButton
import com.kamran.newsapp.presentation.common.NewsTextButton
import com.kamran.newsapp.presentation.onBoarding.components.OnBoardingPage
import com.kamran.newsapp.presentation.onBoarding.components.PageIndicator
import com.kamran.newsapp.ui.theme.NewsAppTheme
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(event: (OnBoardingEvent) -> Unit) {

    Column(modifier = Modifier
        .statusBarsPadding()
        .fillMaxSize()) {
        val pagerState = rememberPagerState {
            pages.size
        }

        val buttonState by remember {
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> listOf("", "Next")
                    pagerState.pageCount - 1 -> listOf("Back", "Get Started")
                    else -> listOf("Back", "Next")
                }
            }
        }


        HorizontalPager(state = pagerState) {
            OnBoardingPage(page = pages[it], modifier = Modifier)
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp, start = 30.dp, end = 30.dp)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PageIndicator(
                modifier = Modifier,
                pageSize = pagerState.pageCount,
                selectedPage = pagerState.currentPage
            )

            Row {

                val scope = rememberCoroutineScope()

                if (buttonState.first().isNotEmpty()) {
                    NewsTextButton(text = buttonState.first()) {
                        scope.launch {
                            pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                        }
                    }
                }
                NewsButton(text = buttonState.last()) {

                    if (pagerState.currentPage == pagerState.pageCount - 1) {
                        event(OnBoardingEvent.SaveAppEntry)
                    } else {
                        scope.launch {
                            pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                        }
                    }
                }
            }
        }


    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun OnBoardingScreenPreview() {
    NewsAppTheme {
        OnBoardingScreen {}
    }
}