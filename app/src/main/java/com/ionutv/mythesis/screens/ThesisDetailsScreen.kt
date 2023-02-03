package com.ionutv.mythesis.screens

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ionutv.mythesis.screens.teacher.TeacherViewModel
import com.ionutv.mythesis.screens.teacher.thesis.ThesisItem
import com.ionutv.mythesis.ui.theme.MyThesisTheme
import androidx.lifecycle.viewmodel.compose.viewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ThesisDetailsScreen(itemNumber: Int, teacherViewModel: TeacherViewModel = viewModel()) {
    val item = ThesisItem(
        ThesisItem.Owner("Jhon Doe", Color.Cyan.copy(0.2f)),
        "Study of AI use in Crimes",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque tristique diam vitae mi faucibus, eu porttitor ex iaculis. Donec lacinia congue massa et rhoncus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nam id turpis arcu. Sed dictum, tortor eget fringilla feugiat, sem quam dictum justo, at suscipit velit nunc at massa. Sed placerat semper nunc dictum suscipit. Donec facilisis quis erat lobortis mattis. Donec at lorem quam. Integer posuere posuere aliquet. Maecenas quis felis vitae lorem ornare vulputate vitae nec sem. Nam hendrerit dapibus enim eu tincidunt. Aliquam ultrices sit amet mi tristique elementum. Suspendisse nec orci a dolor pharetra congue. ",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque tristique diam vitae mi faucibus, eu porttitor ex iaculis. Donec lacinia congue massa et rhoncus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nam id turpis arcu. Sed dictum, tortor eget fringilla feugiat, sem quam dictum justo, at suscipit velit nunc at massa. Sed placerat semper nunc dictum suscipit. Donec facilisis quis erat lobortis mattis. Donec at lorem quam. Integer posuere posuere aliquet. Maecenas quis felis vitae lorem ornare vulputate vitae nec sem. Nam hendrerit dapibus enim eu tincidunt. Aliquam ultrices sit amet mi tristique elementum. Suspendisse nec orci a dolor pharetra congue. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque tristique diam vitae mi faucibus, eu porttitor ex iaculis. Donec lacinia congue massa et rhoncus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nam id turpis arcu. Sed dictum, tortor eget fringilla feugiat, sem quam dictum justo, at suscipit velit nunc at massa. Sed placerat semper nunc dictum suscipit. Donec facilisis quis erat lobortis mattis. Donec at lorem quam. Integer posuere posuere aliquet. Maecenas quis felis vitae lorem ornare vulputate vitae nec sem. Nam hendrerit dapibus enim eu tincidunt. Aliquam ultrices sit amet mi tristique elementum. Suspendisse nec orci a dolor pharetra congue. ",
        "10 Jan",
        true
    )

    var isDescriptionExpanded by remember {
        mutableStateOf(false)
    }
    var isBodyExpanded by remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier.verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
            Column(Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = item.title, fontWeight = FontWeight.Bold, fontSize = 24.sp)
                Text(text = item.owner.name, fontSize = 24.sp)
            }
        }

        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Card(shape = MaterialTheme.shapes.extraSmall) {
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                        .animateContentSize()
                        .clickable {
                            isDescriptionExpanded = !isDescriptionExpanded
                        }
                        .padding(8.dp)
                ) {
                    Text(text = "Description:", fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = item.description,
                        maxLines = if (isDescriptionExpanded) Int.MAX_VALUE else 4,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                    )
                }
            }

            Card(shape = MaterialTheme.shapes.extraSmall) {
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                        .animateContentSize()
                        .clickable {
                            isBodyExpanded = !isBodyExpanded
                        }
                        .padding(8.dp)
                ) {
                    Text(text = "Thesis Body:", fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = item.description,
                        maxLines = if (isBodyExpanded) Int.MAX_VALUE else 8,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                    )
                }
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally), modifier = Modifier.padding(top=20.dp).clip(MaterialTheme.shapes.extraSmall).background(MaterialTheme.colorScheme.tertiaryContainer).clickable {  }.padding(8.dp)) {
            Icon(imageVector = Icons.Default.Download , contentDescription = "Thesis Download Button")
            Text("Download")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    MyThesisTheme {
        ThesisDetailsScreen(
            3
        )
    }
}