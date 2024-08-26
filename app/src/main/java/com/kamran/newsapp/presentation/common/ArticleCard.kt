package com.kamran.newsapp.presentation.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kamran.newsapp.R
import com.kamran.newsapp.domain.model.Article

@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: () -> Unit
) {

    val context = LocalContext.current

    Row(modifier = Modifier
        .clip(MaterialTheme.shapes.medium)
        .clickable { onClick() }) {

        AsyncImage(
            modifier = Modifier
                .size(96.dp)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(context).data(article.urlToImage).build(),
            contentDescription = "Article Image",
        )

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = 6.dp)
                .height(96.dp),
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.labelMedium,
                )

                Spacer(modifier = Modifier.width(6.dp))

                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.time_icon),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = article.publishedAt,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }

    }

}