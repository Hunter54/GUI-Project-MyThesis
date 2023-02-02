package com.ionutv.mythesis

import androidx.compose.animation.Crossfade
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    appBarState: AppBarState,
) {
    TopAppBar(title = {
        Text(text = appBarState.title)
    }, navigationIcon = {
        Crossfade(targetState = appBarState){appBarState ->
            when(appBarState){
                is AppBarState.NavigationDrawer ->{
                    IconButton(onClick = appBarState.onClick) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Toggle Drawer")
                    }
                }
                is AppBarState.BackArrow ->{
                    IconButton(onClick = appBarState.onClick) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back Button")
                    }
                }
            }

        }
    })
}

sealed class AppBarState(open val title: String, open val onClick: () -> Unit) {
    data class NavigationDrawer(
        override val title: String,
        override val onClick: () -> Unit,
    ) : AppBarState(title, onClick);
    data class BackArrow(
        override val title: String,
        override val onClick: () -> Unit,
    ) : AppBarState(title, onClick);
}