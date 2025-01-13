package com.example.alp_vp.views.location

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.alp_vp.R
import com.example.alp_vp.views.templates.BotAppBar
import com.example.alp_vp.views.templates.TopAppBar
import com.example.alp_vp.repositories.MockLocationRepository
import com.example.alp_vp.repositories.MockUserRepository
import com.example.alp_vp.uistates.LocationUIState
import com.example.alp_vp.viewmodels.BukitViewModel

@Composable
fun Bukit(viewModel: BukitViewModel, navController: NavHostController) {
    val locationUIState = viewModel.locationUIState
    LaunchedEffect(Unit) {
        viewModel.getAllBukits()
    }
    if (locationUIState is LocationUIState.Loading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
    if (locationUIState is LocationUIState.Failed) {
        Text("Error: ${locationUIState.errorMessage}")
    }
    if (locationUIState is LocationUIState.Success) {
        val colors = viewModel.getBukitColors()
        val nama = viewModel.getBukitNama()

        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(navController)

            // Content Area
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Spacer(Modifier.height(8.dp))
                    Box(
                        Modifier
                            .background(Color.Gray)
                            .fillMaxWidth()
                            .height(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painterResource(R.drawable.baseline_keyboard_double_arrow_left_24),
                            contentDescription = null
                        )
                    }
                    Row {
                        Box(
                            Modifier
                                .background(Color.Gray)
                                .size(24.dp, 440.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painterResource(R.drawable.baseline_keyboard_double_arrow_down_24),
                                contentDescription = null
                            )
                        }
                        Column {
                            Spacer(Modifier.height(8.dp))
                            Row(
                                Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                for (i in 17 downTo 0) {
                                    VertiBukit(colors[i], nama[i])
                                }
                            }
                            Spacer(Modifier.height(8.dp))
                            Row {
                                Column(Modifier.padding(8.dp)) {
                                    for (i in 18 until 36) {
                                        HorizBukit(colors[i], nama[i])
                                    }
                                }
                                Box(
                                    Modifier
                                        .size(300.dp, 400.dp)
                                        .background(Color(0xff4c6444)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("Bukit", color = Color.White, fontSize = 40.sp)
                                }
                            }
                        }
                    }
                }
            }
            BotAppBar(navController)
        }
    }
}

@Composable
fun VertiBukit(color: Color, num: String) {
    Spacer(Modifier.width(2.dp))
    Box(
        Modifier
            .background(color)
            .size(12.dp, 24.dp)
    ) {
//        Text(num, fontSize = 4.sp)
    }
    Spacer(Modifier.width(2.dp))
}

@Composable
fun HorizBukit(color: Color, num: String) {
    Spacer(Modifier.height(2.dp))
    Box(
        Modifier
            .background(color)
            .size(24.dp, 12.dp)
    ) {
//        Text(num, fontSize = 4.sp)
    }
    Spacer(Modifier.height(2.dp))
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BukitPreview() {
    // Use LocationRepositoryMock instead of the actual LocationRepository
    val viewModel =
        BukitViewModel(MockLocationRepository(), MockUserRepository())  // Inject mock repository
    viewModel.getAllBukits()  // Trigger data loading
    val navController = rememberNavController()
    Bukit(viewModel, navController)
}
