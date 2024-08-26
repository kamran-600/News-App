package com.kamran.newsapp.presentation.onBoarding

sealed class OnBoardingEvent {

    data object SaveAppEntry : OnBoardingEvent()
}