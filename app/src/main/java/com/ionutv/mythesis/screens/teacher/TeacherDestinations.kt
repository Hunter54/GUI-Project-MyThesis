package com.ionutv.mythesis.screens.teacher

sealed class TeacherDestinations(val route: String) {
    object AddEditThesis : TeacherDestinations("teacher/teacherProposed/addEditThesis")
    object ThesisDetails : TeacherDestinations("teacher/thesis/thesisDetails")
    object ProposedThesisDetails : TeacherDestinations("teacher/studentProposed/thesisDetails")
}
