package com.ionutv.mythesis.ui

import android.annotation.SuppressLint
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

const val ANIMATION_DURATION = 500
const val MIN_DRAG_AMOUNT = 6

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun DraggableCard(
    isRevealed: Boolean,
    cardOffset: Float,
    onExpand: () -> Unit,
    onCollapse: () -> Unit,
    content: @Composable () -> Unit,
) {
    val transitionState = remember {
        MutableTransitionState(isRevealed).apply {
            targetState = !isRevealed
        }
    }
    val transition = updateTransition(transitionState, "cardTransition")
    val cardBgColor by transition.animateColor(
        label = "cardBgColorTransition",
        transitionSpec = { tween(durationMillis = ANIMATION_DURATION) },
        targetValueByState = {
            if (isRevealed) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.surface
        }
    )
    val offsetTransition by transition.animateFloat(
        label = "cardOffsetTransition",
        transitionSpec = { tween(durationMillis = ANIMATION_DURATION) },
        targetValueByState = { if (isRevealed) -cardOffset else 0f },
    )

    val cardElevation by transition.animateDp(
        label = "cardElevation",
        transitionSpec = { tween(durationMillis = ANIMATION_DURATION) },
        targetValueByState = { if (isRevealed) 40.dp else 2.dp }
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(IntrinsicSize.Min)
            .offset { IntOffset(offsetTransition.roundToInt(), 0) }
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    when {
                        dragAmount <= MIN_DRAG_AMOUNT -> onExpand()
                        dragAmount > -MIN_DRAG_AMOUNT -> onCollapse()
                    }
                }
            },
        colors = CardDefaults.cardColors(containerColor = cardBgColor),
        shape = MaterialTheme.shapes.medium,
//        elevation = cardElevation,
        content = { content() }
    )
}


@Composable
fun TeacherThesisActionsRow(
    offsetDistance: Dp,
    actionIconSize: Dp = 50.dp,
    onDelete: () -> Unit,
    onComment: () -> Unit,
    onAccept: () -> Unit,
) {
    Row(
        Modifier
            .width(offsetDistance)
            .padding(top = 8.dp, bottom = 8.dp, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        IconButton(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            onClick = onDelete,
            content = {
                Icon(
                    imageVector = Icons.Outlined.ThumbDown,
                    tint = Color.Red,
                    contentDescription = "Decline button",
                    modifier = Modifier.fillMaxSize()
                )
            }
        )
        IconButton(
            modifier = Modifier
                .size(actionIconSize)
                .fillMaxSize()
                .weight(1f),
            onClick = onComment,
            content = {
                Icon(
                    imageVector = Icons.Outlined.Comment,
                    tint = Color.Gray,
                    contentDescription = "Comment Button",
                    modifier = Modifier.fillMaxSize()
                )
            },
        )
        IconButton(
            modifier = Modifier
                .size(actionIconSize)
                .fillMaxSize()
                .weight(1f),
            onClick = onAccept,
            content = {
                Icon(
                    imageVector = Icons.Outlined.ThumbUp,
                    tint = Color.Green,
                    contentDescription = "Approve button",
                    modifier = Modifier.fillMaxSize()
                )
            }
        )
    }
}

@Composable
fun StudentProposedThesisActionsRow(
    offsetDistance: Dp,
    actionIconSize: Dp = 35.dp,
    onReject: () -> Unit,
    onAccept: () -> Unit,
) {
    Row(
        Modifier
            .width(offsetDistance)
            .padding(top = 8.dp, bottom = 8.dp, end = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconButton(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            onClick = onReject,
            content = {
                Icon(
                    imageVector = Icons.Outlined.Block,
                    tint = Color.Red,
                    contentDescription = "Decline button",
                    modifier = Modifier.fillMaxSize()
                )
            }
        )
        IconButton(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            onClick = onAccept,
            content = {
                Icon(
                    imageVector = Icons.Outlined.DoneOutline,
                    tint = Color.Green,
                    contentDescription = "Approve button",
                    modifier = Modifier.fillMaxSize()
                )
            }
        )
    }
}

@Composable
fun ProposedThesisActionsRow(
    offsetDistance: Dp,
    actionIconSize: Dp = 35.dp,
    onDelete: () -> Unit,
) {
    Row(
        Modifier
            .width(offsetDistance)
            .padding(top = 8.dp, bottom = 8.dp, end = 16.dp)
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconButton(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            onClick = onDelete,
            content = {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    tint = Color.Red,
                    contentDescription = "Decline button",
                    modifier = Modifier.fillMaxSize()
                )
            }
        )
    }
}