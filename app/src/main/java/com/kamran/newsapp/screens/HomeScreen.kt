package com.kamran.newsapp.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kamran.newsapp.R

@Composable
fun HomeScreen(modifier: Modifier = Modifier, onBackPressed: () -> Unit) {

    BackHandler {
        onBackPressed()
    }

    LazyColumn {
        items(20) {
            ListItem(modifier)
        }
    }
}


@Composable
fun ListItem(
    modifier : Modifier = Modifier,
    image: Int = R.drawable.ic_launcher_background,
    title: String = "Kamran",
    description: String = "Alam"
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable {  }.padding(horizontal = 15.dp, vertical = 10.dp)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = modifier
                .size(48.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = modifier.size(10.dp))

        Column(modifier = modifier.weight(1f)) {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            Text(text = description, style = MaterialTheme.typography.bodyMedium)
        }

    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ListItemPreview() {
    HomeScreen(onBackPressed = {
    })
}