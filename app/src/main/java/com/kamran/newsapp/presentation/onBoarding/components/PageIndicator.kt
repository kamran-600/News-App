package com.kamran.newsapp.presentation.onBoarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kamran.newsapp.presentation.onBoarding.pages


@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    pageSize: Int,
    selectedPage: Int,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unselectedColor: Color = Color.Gray
) {

    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        repeat(pageSize) {
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .size(16
                        .dp)
                    .clip(CircleShape)
                    .background(if (it == selectedPage) selectedColor else unselectedColor)
            )
        }

    }
}

    @Preview
    @Composable
    private fun PageIndicatorPreview() {
        PageIndicator(pageSize = pages.size, selectedPage = 1)
    }