package com.kamran.newsapp.presentation.onBoarding

import androidx.annotation.DrawableRes
import com.kamran.newsapp.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Stay Informed",
        description = "Get the latest news from around the world.",
        image = R.drawable.on_boarding_img1
    ),
    Page(
        title = "Personalized Content",
        description = "Follow topics and sources you care about.",
        image = R.drawable.on_boarding_img2
    ),
    Page(
        title = "Real-time Updates",
        description = "Stay updated with live news alerts.",
        image = R.drawable.on_boarding_img3
    ))
