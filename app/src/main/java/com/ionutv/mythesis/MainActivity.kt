package com.ionutv.mythesis

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.Back
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.GravityCompat
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ionutv.mythesis.screens.teacher.BottomTeacherNavigation
import com.ionutv.mythesis.screens.teacher.TeacherBottomNavItems
import com.ionutv.mythesis.screens.teacher.TeacherDestinations
import com.ionutv.mythesis.ui.theme.MyThesisTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        onBackPressedDispatcher.addCallback(this) {
//            // Back is pressed... Finishing the activity
//
//        }
        setContent {
            MyThesisTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var appBarState by remember {
        mutableStateOf<AppBarState>(AppBarState.NavigationDrawer("") {
            scope.launch {
                delay(500.milliseconds)
                drawerState.close()
            }
        })
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    appBarState = getAppBarState(navBackStackEntry, scope, drawerState, navController)

    BackHandler(drawerState.isOpen) {
        scope.launch {
            delay(500.milliseconds)
            drawerState.close()
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerHeader()
                DrawerBody(
                    items = listOf(NavigationDrawerItems.Teacher, NavigationDrawerItems.Student),
                    navBackStackEntry = navBackStackEntry,
                    onItemClick = {
                        Toast.makeText(context, "Clicked on ${it.title}", Toast.LENGTH_LONG).show()
                        scope.launch {
                            delay(500.milliseconds)
                            drawerState.close()
                        }
                    }
                )
            }

        }) {
        Scaffold(
            bottomBar = { BottomTeacherNavigation(navController) },
            topBar = {
                AppBar(appBarState)
            },
        ) { paddingValues ->

            Navigation(
                modifier = Modifier.padding(
                    bottom = paddingValues.calculateBottomPadding(),
                    top = paddingValues.calculateTopPadding()
                ),
                navController
            )
        }
    }
}

@Composable
private fun getAppBarState(
    navBackStackEntry: NavBackStackEntry?,
    scope: CoroutineScope,
    drawerState: DrawerState,
    navController: NavHostController,
) = when (navBackStackEntry?.destination?.route) {
    TeacherBottomNavItems.TeacherProposed.route -> {
        AppBarState.NavigationDrawer(stringResource(id = R.string.app_name)) {
            scope.launch {
                drawerState.open()
            }
        }
    }
    TeacherBottomNavItems.TeacherThesis.route -> {
        AppBarState.NavigationDrawer(stringResource(id = R.string.app_name)) {
            scope.launch {
                drawerState.open()
            }
        }
    }
    TeacherBottomNavItems.StudentProposed.route -> {
        AppBarState.NavigationDrawer(stringResource(id = R.string.app_name)) {
            scope.launch {
                drawerState.open()
            }
        }
    }
    TeacherDestinations.AddEditThesis.route ->{
        AppBarState.BackArrow("Add Thesis") {
            navController.navigateUp()
        }
    }
    else -> {
        AppBarState.BackArrow("Thesis Details") {
            navController.navigateUp()
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyThesisTheme {
        MainScreen()
    }
}

fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("no activity")
}