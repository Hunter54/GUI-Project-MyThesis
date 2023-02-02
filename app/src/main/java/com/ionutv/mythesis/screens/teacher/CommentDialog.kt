package com.ionutv.mythesis.screens.teacher

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.ionutv.mythesis.ui.theme.MyThesisTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogForSendingComment(
    itemNr: Int,
    modifier: Modifier = Modifier,
    onSubmit: (String) -> Unit,
) {
    var text by remember {
        mutableStateOf("")
    }
    Dialog(onDismissRequest = { onSubmit(text) }) {
        Box(
            modifier = modifier
                .clip(shape = MaterialTheme.shapes.extraLarge)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(10.dp)

        ) {
            Column(
                modifier= Modifier.height(IntrinsicSize.Min),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    "Leave your comment",
                    modifier = Modifier.padding(vertical = 10.dp),
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
                OutlinedTextField(modifier = Modifier.height(200.dp),value = text, label = { Text(text = "Comment") }, onValueChange = { text = it })

                Button(onClick = { onSubmit(text) }) {
                    Text(text = "Submit")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DialogForSendingCommentPreview() {
    MyThesisTheme {
        DialogForSendingComment(itemNr = 1, onSubmit = {})
    }
}