package ru.bogdan.application.presentor.ui.details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import ru.bogdan.application.HomeApp
import ru.bogdan.application.R
import ru.bogdan.application.domain.Tool

@Composable
fun DetailsEmployee(
    employeeId: String,
    modifier: Modifier = Modifier,
    onPressBack: () -> Unit,
) {
    val component = (LocalContext.current.applicationContext as HomeApp)
        .appComponent
        .getViewModelComponentFactory()
        .create(employeeId)

    val viewModel: DetailsEmployeeViewModel = viewModel(factory = component.getViewModelFactory())
    val state = viewModel.state.collectAsState()
    DetailsEmployeeContent(
        state = state,
        modifier = modifier,
        onPressBack = {
            viewModel.deleteEmployee()
            onPressBack()
        },
    )

}

@Composable
fun DetailsEmployeeContent(
    state: State<DetailsState>,
    modifier: Modifier = Modifier,
    onPressBack: () -> Unit
) {
    Box(modifier = modifier) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AsyncImage(
                modifier = Modifier
                    .size(350.dp)
                    .padding(16.dp)
                    .clip(CircleShape),
                model = state.value.employee.photoUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = state.value.employee.name, fontSize = 35.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = state.value.employee.surname, fontSize = 35.sp, fontWeight = FontWeight.Bold)
            ToolsAnimated(
                tools = state.value.employee.tools,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedButton(
                onClick = {
                    onPressBack()
                }
            ) {
                Text(
                    text = stringResource(R.string.delete_employee)
                )
            }
        }

        Icon(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(24.dp)
                .size(64.dp),
            imageVector = if (state.value.employee.isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = "",
            tint = Color.Red
        )

        if (state.value.isLoading)
            CircularProgressIndicator()
    }
}

@Composable
fun ToolsAnimated(
    tools: List<Tool>,
    modifier: Modifier = Modifier
) {

    var isOpen by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.tools),
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
            )
            Icon(
                imageVector = if (isOpen) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
                contentDescription = "",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(24.dp)
                    .background(Color.Gray)
                    .clickable {
                        isOpen = !isOpen
                    }
            )
        }
        AnimatedVisibility(isOpen) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(16.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(10.dp)),
            ) {
                items(items = tools, key = { it.code }) { tool ->
                    ToolItem(tool)
                }
            }
        }
    }
}

@Preview
@Composable
private fun ToolsAnimatedPreview() {
    ToolsAnimated(
        tools = listOf(),
        modifier = Modifier.padding(top = 160.dp),
    )
}


@Composable
fun ToolItem(
    tool: Tool,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.padding(16.dp)) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                model = tool.photoUrl,
                contentScale = ContentScale.FillBounds,
                contentDescription = null,
            )
            Text(
                modifier = Modifier.weight(1f),
                text = tool.code,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        }

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = tool.name,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = tool.type.name,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
        )
        tool.description?.let {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = tool.description,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
            )
        }

    }
}