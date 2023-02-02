package com.ionutv.mythesis.screens.teacher.thesis

import androidx.compose.ui.graphics.Color

data class ThesisItem(
    val owner: Owner,
    val title: String,
    val description: String,
    val body: String,
    val lastDate: String,
    val isUnread: Boolean,
) {
    data class Owner(val name: String, val color: Color)
}
