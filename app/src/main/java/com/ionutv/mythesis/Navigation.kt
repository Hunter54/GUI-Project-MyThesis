package com.ionutv.mythesis

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ionutv.mythesis.screens.SubmitScreen
import com.ionutv.mythesis.screens.ThesisDetailsScreen
import com.ionutv.mythesis.screens.teacher.*

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavigationDrawerItems.Teacher.route
    ) {
        teacherGraph(navController)
    }
}

fun NavGraphBuilder.teacherGraph(navController: NavController) {
    navigation(
        startDestination = TeacherBottomNavItems.TeacherThesis.route,
        route = NavigationDrawerItems.Teacher.route

    ) {
        composable(TeacherBottomNavItems.TeacherThesis.route) {
            TeacherThesisScreen(navController)
        }

        composable(TeacherBottomNavItems.StudentProposed.route) {
            TeacherStudentProposedScreen(navController)
        }

        composable(TeacherBottomNavItems.TeacherProposed.route) {
            TeacherProposedScreen(navController)
        }

        composable(TeacherDestinations.AddEditThesis.route) {
            SubmitScreen()
        }

        composable(
            TeacherDestinations.ThesisDetails.route+"/{itemNr}",
            arguments = listOf(navArgument("itemNr") { type = NavType.IntType })
        ) {
            ThesisDetailsScreen(it.arguments?.getInt("itemNr") ?: 0)
        }

        composable(
            TeacherDestinations.ProposedThesisDetails.route+"/{itemNr}",
            arguments = listOf(navArgument("itemNr") { type = NavType.IntType })
        ) {
            ThesisDetailsScreen(it.arguments?.getInt("itemNr") ?: 0, shouldShowBody = false)
        }
    }
}