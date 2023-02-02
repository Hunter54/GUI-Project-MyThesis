package com.ionutv.mythesis

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HistoryEdu
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.outlined.HistoryEdu
import androidx.compose.material.icons.outlined.School
import com.ionutv.mythesis.ui.NavigationIcons

sealed class NavigationDrawerItems(
    val route: String,
    val icon: NavigationIcons,
    val title: String,
    val contentDescription: String,
) {
    object Teacher : NavigationDrawerItems(
        "teacher",
        NavigationIcons(Icons.Outlined.HistoryEdu, Icons.Filled.HistoryEdu),
        "Teacher",
        "Teacher Navigation Item"
    )

    object Student : NavigationDrawerItems(
        "student",
        NavigationIcons(Icons.Outlined.School, Icons.Filled.School),
        "Student",
        "Student Navigation Item"
    )
}
