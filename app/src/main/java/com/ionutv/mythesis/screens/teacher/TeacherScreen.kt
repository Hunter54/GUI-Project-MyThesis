package com.ionutv.mythesis.screens.teacher

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ionutv.mythesis.screens.teacher.thesis.MyThesisRow
import com.ionutv.mythesis.screens.teacher.thesis.ThesisItem
import com.ionutv.mythesis.screens.teacher.thesis.ThesisRow
import com.ionutv.mythesis.ui.*
import com.ionutv.mythesis.ui.theme.MyThesisTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomTeacherNavigation(
    navController: NavController,
) {
    val destinations = listOf(
        TeacherBottomNavItems.TeacherThesis,
        TeacherBottomNavItems.StudentProposed,
        TeacherBottomNavItems.TeacherProposed
    )

    NavigationBar(
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        destinations.forEach { item ->
            val selected = currentDestination?.hierarchy?.any {
                it.route?.contains(item.route) ?: false
            } == true
            NavigationBarItem(
                selected = selected,
                icon = {
                    if (selected) Icon(
                        item.icon.filled,
                        "Navigation Icon"
                    ) else Icon(item.icon.outlined, "Navigation Icon")
                },
                label = { Text(text = item.title) },
                alwaysShowLabel = true,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
        }
    }
}

@Composable
fun TeacherThesisList(
    thesisItems: List<ThesisItem>,
    revealedItems: List<ThesisItem>,
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    onItemExpanded: (ThesisItem) -> Unit,
    onItemCollapsed: (ThesisItem) -> Unit,
    actionsRow: @Composable (Dp) -> Unit,
    thesisRow: @Composable (ThesisItem) -> Unit,
) {
    LazyColumn(state = listState) {
        items(thesisItems) { item ->
            BoxWithConstraints(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                val maxWidthDp = this.maxWidth
                val offsetDp = maxWidthDp.times(0.4f)
                val offsetPx = LocalDensity.current.run { offsetDp.toPx() }
                actionsRow(offsetDp)
                DraggableCard(
                    isRevealed = revealedItems.contains(item),
                    cardOffset = offsetPx,
                    onExpand = { onItemExpanded(item) },
                    onCollapse = { onItemCollapsed(item) }) {
                    thesisRow(item)
                }
            }
        }
    }
}

@Composable
fun TeacherThesisScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    teacherViewModel: TeacherViewModel = viewModel(),
) {
    val thesisItems by teacherViewModel.thesises.collectAsStateWithLifecycle()
    val revealedItems by teacherViewModel.revealedThesisesIdsList.collectAsStateWithLifecycle()
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TeacherThesisList(thesisItems, revealedItems, onItemExpanded = {
            teacherViewModel.onItemExpanded(it)
        }, onItemCollapsed = {
            teacherViewModel.onItemCollapsed(it)
        }, actionsRow = { offsetDp ->
            TeacherThesisActionsRow(offsetDp,
                onDelete = {},
                onComment = {},
                onAccept = {}
            )
        }) {
            val itemIndex = thesisItems.indexOf(it)
            ThesisRow(item = it) {
                navController.navigate(TeacherDestinations.ThesisDetails.route + "/$itemIndex")
            }
        }
    }
}

@Composable
fun TeacherStudentProposedScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    teacherViewModel: TeacherViewModel = viewModel(),
) {

    val thesisItems by teacherViewModel.studentProposedThesis.collectAsStateWithLifecycle()
    val revealedItems by teacherViewModel.revealedThesisesIdsList.collectAsStateWithLifecycle()
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TeacherThesisList(thesisItems, revealedItems, onItemExpanded = {
            teacherViewModel.onItemExpanded(it)
        }, onItemCollapsed = {
            teacherViewModel.onItemCollapsed(it)
        }, actionsRow = { offsetDp ->
            StudentProposedThesisActionsRow(offsetDp,
                onReject = {},
                onAccept = {}
            )
        }) {item ->
            ThesisRow(item = item) {
                val itemIndex = thesisItems.indexOf(it)
                navController.navigate(TeacherDestinations.ProposedThesisDetails.route + "/$itemIndex")
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeacherProposedScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    teacherViewModel: TeacherViewModel = viewModel(),
) {
    val lazyListState = rememberLazyListState()
    val thesisItems by teacherViewModel.proposedThesises.collectAsStateWithLifecycle()
    val revealedItems by teacherViewModel.revealedThesisesIdsList.collectAsStateWithLifecycle()
    Scaffold(
        floatingActionButton = {
            FloatingActionButtonAdd(extended = lazyListState.isScrollingUp(),onClick = {
                navController.navigate(TeacherDestinations.AddEditThesis.route) {
                    restoreState
                }
            })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(
                    bottom = paddingValues.calculateBottomPadding(),
                    top = paddingValues.calculateTopPadding()
                ),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TeacherThesisList(thesisItems, revealedItems, listState = lazyListState, onItemExpanded = {
                teacherViewModel.onItemExpanded(it)
            }, onItemCollapsed = {
                teacherViewModel.onItemCollapsed(it)
            }, actionsRow = { offsetDp ->
                ProposedThesisActionsRow(
                    offsetDp,
                    onDelete = {},
                )
            }) {
                MyThesisRow(item = it)
            }
        }
    }

}

@Composable
private fun FloatingActionButtonAdd(extended: Boolean, onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick ){
        Row(modifier = Modifier.padding(horizontal = 16.dp)){
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Button for adding a new Proposal"
            )
            AnimatedVisibility(visible = extended) {
                Text(
                    text = "Add",
                    modifier = Modifier
                        .padding(start = 8.dp, top = 3.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    val navController = rememberNavController()
    val items = listOf(
        ThesisItem(
            ThesisItem.Owner("Jhon Doe", Color.Blue.copy(0.2f)),
            "Study of AI use in Crimes",
            "Lorem ipsum salvador ipsum lore impas amesta von comen into the room with you",
            "Lorem ipsum salvador ipsum lore impas amesta von comen into the room with youLorem ipsum salvador ipsum lore impas amesta von comen into the room with you",
            "10 Jan",
            true
        ),
        ThesisItem(
            ThesisItem.Owner("Jhoana Doeser", Color.Blue.copy(0.2f)),
            "Study of AI use in Crimes against the city of this place",
            "Lorem ipsum salvador ipsum lore impas amesta von comen into the room with you",
            "Lorem ipsum salvador ipsum lore impas amesta von comen into the room with youLorem ipsum salvador ipsum lore impas amesta von comen into the room with you",
            "10 Jan",
            false
        ),
        ThesisItem(
            ThesisItem.Owner("Jhoana Doeser", Color.Blue.copy(0.2f)),
            "Study of AI use in Crimes against the city of this place",
            "Lorem ipsum salvador ipsum lore impas amesta von comen into the room with you",
            "Lorem ipsum salvador ipsum lore impas amesta von comen into the room with youLorem ipsum salvador ipsum lore impas amesta von comen into the room with you",
            "10 Jan",
            true
        )
    )
    val revealedItems = remember {
        mutableStateListOf<ThesisItem>()
    }
    MyThesisTheme {
        Column {
            TeacherThesisList(items,
                revealedItems,
                modifier = Modifier.weight(1f),
                onItemCollapsed = { revealedItems.remove(it) },
                onItemExpanded = { revealedItems.add(it) }, actionsRow = {
                    TeacherThesisActionsRow(
                        offsetDistance = it,
                        onDelete = { /*TODO*/ },
                        onComment = { /*TODO*/ }) {

                    }
                }) {
                ThesisRow(item = it) {

                }
            }
            BottomTeacherNavigation(navController)
        }
    }
}