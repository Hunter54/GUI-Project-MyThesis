package com.ionutv.mythesis

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ionutv.mythesis.ui.theme.MyThesisTheme

@Composable
fun DrawerHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 64.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Icon(
            modifier = Modifier.size(60.dp),
            imageVector = Icons.Default.AccountBox,
            contentDescription = "User Profile Picture"
        )
        Text(text = "Current User", fontSize = 30.sp)
    }
}

@Composable
fun DrawerBodyItem(
    item: NavigationDrawerItems,
    onItemClick: (NavigationDrawerItems) -> Unit,
    itemTextStyle: TextStyle = TextStyle(fontSize = 30.sp),
) {
    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(40.dp))
            .fillMaxWidth()
            .clickable {
                onItemClick(item)
            }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = item.icon.filled, contentDescription = item.contentDescription)
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = item.title, style = itemTextStyle,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun DrawerBody(
    items: List<NavigationDrawerItems>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 30.sp),
    onItemClick: (NavigationDrawerItems) -> Unit,
) {
    LazyColumn(modifier = Modifier) {
        items(items) { item ->
            DrawerBodyItem(item = item, onItemClick, itemTextStyle)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DrawerBodyPreview() {
    MyThesisTheme {
        Column() {
            DrawerHeader()
            DrawerBodyItem(NavigationDrawerItems.Teacher, onItemClick = {})
        }
    }
}