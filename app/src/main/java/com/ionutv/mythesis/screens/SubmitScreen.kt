package com.ionutv.mythesis.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun SubmitScreen(modifier: Modifier = Modifier) {
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .animateContentSize()
    ) {
        var title by rememberSaveable { mutableStateOf("") }
        var text by rememberSaveable { mutableStateOf("") }
        TextField(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .animateContentSize(), value = title,
            label = { Text(text = "Title") },
            onValueChange = {
                title = it
            })

        TextField(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f)
            .imePadding()
            .padding(8.dp)
            .animateContentSize()
            .bringIntoViewRequester(bringIntoViewRequester)
            .onFocusEvent {
                if (it.isFocused) {
                    coroutineScope.launch {
                        bringIntoViewRequester.bringIntoView()
                    }
                }
            }, value = text,
            label = { Text(text = "Small Description") },
            onValueChange = {
                text = it
            })

        Button(modifier = Modifier.align(CenterHorizontally), onClick = { /*TODO*/ }) {
            Text(text = "Submit")
        }
    }
}