package com.example.alp_vp.views.loginregister

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.alp_vp.enums.PagesEnum
import com.example.alp_vp.uistates.AuthenticationStatusUIState
import com.example.alp_vp.viewmodels.AuthenticationViewModel
import com.example.alp_vp.views.templates.LogoBig

@Composable
fun Register(
    authenticationViewModel: AuthenticationViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    context: Context
) {
    val registerUIState by authenticationViewModel.authenticationUIState.collectAsState()

    LaunchedEffect(authenticationViewModel.dataStatus) {
        val dataStatus = authenticationViewModel.dataStatus
        if (dataStatus is AuthenticationStatusUIState.Failed) {
            Toast.makeText(context, dataStatus.errorMessage, Toast.LENGTH_SHORT).show()
            authenticationViewModel.clearErrorMessage()
        }
    }

    Column(
        Modifier
            .background(color = Color(0xffffa001))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LogoBig()
        Spacer(Modifier.height(24.dp))
        Column(
            Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(color = Color(0xffffe3c7))
                .width(300.dp)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = authenticationViewModel.usernameInput,
                onValueChange = {
                    authenticationViewModel.changeUsernameInput(it)
                    authenticationViewModel.checkRegisterForm()
                },
                Modifier.fillMaxWidth(),
                label = { Text("Username") },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White
                )
            )
            Spacer(Modifier.height(12.dp))
            TextField(
                value = authenticationViewModel.passwordInput,
                onValueChange = {
                    authenticationViewModel.changePasswordInput(it)
                    authenticationViewModel.checkRegisterForm()
                },
                Modifier.fillMaxWidth(),
                label = { Text("Password") },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White
                )
            )
            Spacer(Modifier.height(24.dp))
            Button(
                onClick = { authenticationViewModel.registerUser(navController) },
                Modifier.fillMaxWidth(),
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
                    text = "Login",
                    color = Color(0xffffa001),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W500,
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .clickable {
                            navController.navigate(PagesEnum.Login.name)
                        }
                )

            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterPreview() {
    Register(
        authenticationViewModel = viewModel(),
        navController = rememberNavController(),
        context = LocalContext.current
    )
}
