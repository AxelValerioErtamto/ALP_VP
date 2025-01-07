package com.example.alp_vp

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alp_vp.ui.theme.ALP_VPTheme

@Composable
fun Register() {
    Column(
        Modifier
            .background(color = Color(0xffffa001))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Text("Park", color = Color.White, fontSize = 36.sp, fontWeight = FontWeight.W600)
            Spacer(Modifier.width(2.dp))
            Box(
                Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color.White)
                    .padding(horizontal = 4.dp, vertical = 2.dp)
            ) {
                Text(
                    "hub",
                    color = Color(0xffffa001),
                    fontSize = 36.sp,
                    fontWeight = FontWeight.W600
                )
            }
        }
        Column(
            Modifier
                .shadow(4.dp, RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(16.dp))
                .background(color = Color(0xffffe3c7))
                .width(300.dp)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = "",
                onValueChange = { },
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
                label = { Text("Username") },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Black,
                    unfocusedLabelColor = Color.Gray,
                    focusedLabelColor = Color.Gray
                )
            )
            Spacer(Modifier.height(12.dp))
            TextField(
                value = "",
                onValueChange = { },
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
                label = { Text("Password") },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Black,
                    unfocusedLabelColor = Color.Gray,
                    focusedLabelColor = Color.Gray
                )
            )
            Spacer(Modifier.height(24.dp))
            Button(
                onClick = {}, Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xffffa001),
                    contentColor = Color.White
                )
            ) {
                Text("Register", fontSize = 18.sp, fontWeight = FontWeight.W400)
            }
            Row {
                Text(
                    "Already have an account? ",
                    color = Color.Black,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W400
                )
                Text(
                    "Login",
                    color = Color(0xffffa001),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W500
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterPreview() {
    ALP_VPTheme {
        Register()
    }
}