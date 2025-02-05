package com.example.alp_vp.views.report

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alp_vp.R
import com.example.alp_vp.models.ReportModel
import com.example.alp_vp.ui.theme.ALP_VPTheme
import com.example.alp_vp.viewmodels.ReportViewModel
import com.example.alp_vp.views.lesson.NavigationItem

@Composable
fun ReportPageView(
    modifier: Modifier = Modifier,
    reportViewModel: ReportViewModel,
    token: String,
    context: Context
) {
    val reports by reportViewModel.reportById.collectAsState()

    // Trigger fetching reports when the page is displayed
    LaunchedEffect(Unit) {
        reportViewModel.getAllReports(token = token)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier
        ) {
            // TopAppBar
            Column(
                modifier = Modifier
                    .background(color = Color(0xffffa001))
                    .fillMaxWidth()
                    .padding(top = 40.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            "Park",
                            color = Color.White,
                            fontSize = 40.sp,
                            fontWeight = FontWeight.W700
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Box(
                            Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.White)
                                .padding(horizontal = 6.dp, vertical = 4.dp)
                        ) {
                            Text(
                                "hub",
                                color = Color(0xffffa001),
                                fontSize = 40.sp,
                                fontWeight = FontWeight.W700
                            )
                        }
                    }
                }
            }

            // Reports Section
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(reports) { report ->
                    ReportItem(report = report)
                    Divider(color = Color.Gray, thickness = 1.dp)
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(color = Color(0xffffa001))
                .padding(vertical = 16.dp), // Increased padding
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavigationItem(R.drawable.warning, "Report", iconSize = 32.dp, fontSize = 14.sp)
            NavigationItem(R.drawable.car, "Location", iconSize = 32.dp, fontSize = 14.sp)
            NavigationItem(R.drawable.book, "Lesson", iconSize = 32.dp, fontSize = 14.sp)
        }
    }
}



@Composable
fun ReportItem(report: ReportModel) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // User Icon Placeholder
            Image(
                painter = painterResource(id = R.drawable.ic_person),
                contentDescription = "User Icon",
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(50))
            )
            Spacer(modifier = Modifier.width(8.dp))
            // User Name
            Text(
                text = "User ID: ${report.userId}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Uploaded Image Placeholder
            Image(
                painter = painterResource(id = R.drawable.lambo), // Replace with actual logic for image loading
                contentDescription = "Uploaded Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )


        Spacer(modifier = Modifier.height(8.dp))

        // Title
        Text(
            text = report.title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Description
        Text(
            text = report.description,
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun ReportPagePreview() {
    val mockReports = listOf(
        ReportModel(1, 1, "Mock Description 1", "uciw", "",),
        ReportModel(2, 2, "Mock Description 2", "bhc", "")
    )

    ALP_VPTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            mockReports.forEach { report ->
                ReportItem(report = report)
                Divider(color = Color.Gray, thickness = 1.dp)
            }
        }
    }
}

