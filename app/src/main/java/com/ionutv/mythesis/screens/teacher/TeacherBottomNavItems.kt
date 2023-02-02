package com.ionutv.mythesis.screens.teacher

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import com.ionutv.mythesis.ui.NavigationIcons

sealed class TeacherBottomNavItems(val route: String, val icon:NavigationIcons, val title:String){

    object TeacherThesis : TeacherBottomNavItems("teacher/thesis", NavigationIcons(Icons.Outlined.Home, Icons.Filled.Home),"Thesis")
    object StudentProposed : TeacherBottomNavItems("teacher/studentProposed", NavigationIcons(Icons.Outlined.Group, Icons.Filled.Group),"Proposals")
    object TeacherProposed : TeacherBottomNavItems("teacher/teacherProposed",NavigationIcons(Icons.Outlined.NoteAdd, Icons.Filled.NoteAdd),"Add")

}
