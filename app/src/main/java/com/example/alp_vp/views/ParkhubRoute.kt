

package com.example.parkhub.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.alp_vp.enums.PagesEnum
import com.example.alp_vp.viewmodels.AuthenticationViewModel
import com.example.alp_vp.viewmodels.HomeViewModel
import com.example.alp_vp.viewmodels.ReportViewModel
import com.example.alp_vp.views.home.AdminPage
import com.example.alp_vp.views.home.HomePage
import com.example.alp_vp.views.lesson.AdminCreateLesson
import com.example.alp_vp.views.lesson.AdminManageLesson
import com.example.alp_vp.views.loginregister.Login
import com.example.alp_vp.views.loginregister.Register
import com.example.alp_vp.views.report.ReportPageView
import com.example.alp_vp.views.report.SubmitReportView

@Composable
fun ParkhubApp(
    navController: NavHostController = rememberNavController(),
    homeViewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory),
    authenticationViewModel: AuthenticationViewModel = viewModel(factory = AuthenticationViewModel.Factory),
    reportViewModel: ReportViewModel = viewModel(factory = ReportViewModel.Factory)
) {
    val localContext = LocalContext.current
    val token = homeViewModel.token.collectAsState()
    val id = homeViewModel.id.collectAsState()

    NavHost(navController = navController, startDestination = PagesEnum.Login.name) {
        composable(route = PagesEnum.Login.name) {
            Login(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                authenticationViewModel = authenticationViewModel,
                navController = navController,
                context = localContext
            )
        }

        composable(route = PagesEnum.Register.name) {
            Register(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                authenticationViewModel = authenticationViewModel,
                navController = navController,
                context = localContext
            )
        }

        composable(route = PagesEnum.Home.name) {
            HomePage(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                homeViewModel = homeViewModel,
                navController = navController,
                token = token.value,
                context = localContext
            )
        }

        composable(route = PagesEnum.Admin.name) {
            AdminPage(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                homeViewModel = homeViewModel,
                navController = navController,
                token = token.value,
                context = localContext
            )
        }

        composable(route = PagesEnum.CreateLesson.name) {
            AdminCreateLesson(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                homeViewModel = homeViewModel,
                navController = navController,
                token = token.value,
                context = localContext
            )
        }

        composable(route = PagesEnum.ManageLesson.name) {
            AdminManageLesson(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                homeViewModel = homeViewModel,
                navController = navController,
                token = token.value,
                context = localContext
            )
        }

        composable(route = PagesEnum.CreateReport.name) {
            SubmitReportView(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                reportViewModel = reportViewModel,
                navController = navController,
                token = token.value,
                id = id.value,
                context = localContext
            )
        }

        composable(route = PagesEnum.ReportPage.name){
            ReportPageView(
                reportViewModel = reportViewModel,
                token = token.value,
                context = localContext
            )
        }
    }
}