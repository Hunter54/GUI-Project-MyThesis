package com.ionutv.mythesis.screens.teacher.thesis

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ionutv.mythesis.ui.theme.MyThesisTheme

@Composable
fun ThesisRow(item: ThesisItem, modifier: Modifier = Modifier, onClick: (ThesisItem) -> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .height(IntrinsicSize.Min)
            .clickable {
                onClick(item)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {

        val weight = if (item.isUnread) FontWeight.Bold else FontWeight.Light

        Surface(
            shape = CircleShape,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .size(40.dp),
            color = item.owner.color
        ) {
            Text(
                text = item.owner.name.first().toString(),
                modifier.wrapContentSize(Alignment.Center)
            )
        }
        Column(
            modifier = modifier
                .padding(horizontal = 8.dp)
                .weight(1f)
        ) {
            Text(
                text = item.owner.name,
                fontWeight = weight,
                fontSize = 18.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = item.title,
                fontWeight = weight,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = item.description,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Light
            )

        }
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(text = item.lastDate)
            Text(text = "40%")
        }
    }
}

@Composable
fun StudentProposedThesisRow(item: ThesisItem, modifier: Modifier = Modifier, onClick: (ThesisItem) -> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .height(IntrinsicSize.Min)
            .clickable {
                onClick(item)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {

        val weight = if (item.isUnread) FontWeight.Bold else FontWeight.Light

        Surface(
            shape = CircleShape,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .size(40.dp),
            color = item.owner.color
        ) {
            Text(
                text = item.owner.name.first().toString(),
                modifier.wrapContentSize(Alignment.Center)
            )
        }
        Column(
            modifier = modifier
                .padding(horizontal = 8.dp)
                .weight(1f)
        ) {
            Text(
                text = item.owner.name,
                fontWeight = weight,
                fontSize = 18.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = item.title,
                fontWeight = weight,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = item.description,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Light
            )

        }
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(text = item.lastDate)
        }
    }
}

@Composable
fun MyThesisRow(item: ThesisItem, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically
    ) {

        val weight = if (item.isUnread) FontWeight.Bold else FontWeight.Light

        Surface(
            shape = CircleShape,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .size(40.dp),
            color = item.owner.color
        ) {
            Text(
                text = item.owner.name.first().toString(),
                modifier.wrapContentSize(Alignment.Center)
            )
        }
        Column(
            modifier = modifier
                .padding(horizontal = 8.dp)
                .weight(1f)
        ) {
            Text(
                text = item.title,
                fontWeight = weight,
                fontSize = 18.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = item.description,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Light
            )

        }
        Column() {
            Text(text = item.lastDate)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ThesisRowPreview() {
    val item = ThesisItem(
        ThesisItem.Owner("Jhon Doe", Color.Blue.copy(0.2f)),
        "Study of AI use in Crimes and other hilarious stuff",
        "Lorem ipsum salvador ipsum lore impas amesta von comen into the room with you",
        "Lorem ipsum salvador ipsum lore impas amesta von comen into the room with youLorem ipsum salvador ipsum lore impas amesta von comen into the room with you",
        "10 Jan",
        true
    )
    MyThesisTheme {
        Column() {
            ThesisRow(
                item = item
            ){}
            ThesisRow(item = item.copy(isUnread = false)){}
            MyThesisRow(item)
        }
    }
}