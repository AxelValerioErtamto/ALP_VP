package com.example.alp_vp.views.report

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.alp_vp.ui.theme.ALP_VPTheme

data class Report(
    val userName: String,
    val userImageRes: Int,
    val uploadedImageRes: Int,
    val title: String,
    val description: String
)

@Composable
fun ReportPageView() {
    val reports = listOf(
        Report(
            userName = "John Doe",
            R.drawable.ic_person,
            uploadedImageRes = R.drawable.lambo,
            title = "Parking Spot Report",
            description = "There seems to be a blocked parking spot at Lot 5, please check."
        ),
        Report(
            userName = "Jane Smith",
            R.drawable.ic_person,
            uploadedImageRes = R.drawable.lambo,
            title = "Safety Concern",
            description = "The lighting in the parking lot is inadequate. Please address this issue."
        )
        // Add more reports as needed
    )

    Column(modifier = Modifier.fillMaxSize()) {
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

        // Reports Section using LazyColumn
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(reports) { report ->
                ReportItem(report = report)
                Divider(color = Color.Gray, thickness = 1.dp) // Divider between each report
            }
        }
    }
}

@Composable
fun ReportItem(report: Report) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // User Icon
            Image(
                painter = painterResource(id = report.userImageRes),
                contentDescription = "User Icon",
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(50))
            )
            Spacer(modifier = Modifier.width(8.dp))
            // User Name
            Text(
                text = report.userName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Uploaded Image
        Image(
            painter = painterResource(id = report.uploadedImageRes),
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ReportPagePreview() {
    ALP_VPTheme {
        ReportPageView()
    }
}
