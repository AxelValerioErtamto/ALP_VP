package com.example.alp_vp.views

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
import com.example.alp_vp.viewmodels.BukitViewModel
import com.example.alp_vp.viewmodels.HomeViewModel
import com.example.alp_vp.viewmodels.LapanganViewModel
import com.example.alp_vp.viewmodels.ReportViewModel
import com.example.alp_vp.viewmodels.AdminCreateLessonViewModel
import com.example.alp_vp.viewmodels.GedungViewModel
import com.example.alp_vp.viewmodels.AdminUpdateLessonViewModel
import com.example.alp_vp.viewmodels.lesson.LessonViewModel
import com.example.alp_vp.views.home.AdminPage
import com.example.alp_vp.views.home.HomePage
import com.example.alp_vp.views.lesson.AdminCreateLesson
import com.example.alp_vp.views.lesson.AdminManageLesson
import com.example.alp_vp.views.lesson.AdminUpdateLesson
import com.example.alp_vp.views.lesson.LessonDetail
import com.example.alp_vp.views.lesson.LessonPage
import com.example.alp_vp.views.location.Bukit
import com.example.alp_vp.views.location.Gedung
import com.example.alp_vp.views.location.Lapangan
import com.example.alp_vp.views.location.LocationPageView
import com.example.alp_vp.views.loginregister.Login
import com.example.alp_vp.views.loginregister.Register
import com.example.alp_vp.views.report.ReportPageView
import com.example.alp_vp.views.report.SubmitReportView

@Composable
fun ParkhubApp(
    navController: NavHostController = rememberNavController(),
    homeViewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory),
    authenticationViewModel: AuthenticationViewModel = viewModel(factory = AuthenticationViewModel.Factory),
    reportViewModel: ReportViewModel = viewModel(factory = ReportViewModel.Factory),
    adminCreateLessonViewModel: AdminCreateLessonViewModel = viewModel(factory = AdminCreateLessonViewModel.Factory),
    adminUpdateLessonViewModel: AdminUpdateLessonViewModel = viewModel(factory = AdminUpdateLessonViewModel.Factory),
    lessonViewModel: LessonViewModel = viewModel(factory = LessonViewModel.Factory),
    bukitViewModel: BukitViewModel = viewModel(factory = BukitViewModel.Factory),
    lapanganViewModel: LapanganViewModel = viewModel(factory = LapanganViewModel.Factory),
    gedungViewModel: GedungViewModel = viewModel(factory = GedungViewModel.Factory)
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

        composable(route = PagesEnum.LessonPage.name) {
            LessonPage(
                lessonViewModel = lessonViewModel,
                token = token.value,
                onLessonClick = { lesson ->
                    navController.navigate("${PagesEnum.LessonDetail.name}/${lesson.id}")
                }
            )
        }

        // In LessonPage
        composable(route = "${PagesEnum.LessonDetail.name}/{lessonId}") { backStackEntry ->
            val lessonId = backStackEntry.arguments?.getString("lessonId")?.toInt()
            // Assuming you can fetch lesson details using lessonViewModel
            lessonId?.let { id ->
                val lesson = lessonViewModel.getLessonById(id) // Fetch lesson by ID
                lesson?.let {
                    LessonDetail(
                        lesson = it, // Pass the fetched lesson
                        onBackClick = { navController.popBackStack() } // Handle back navigation
                    )
                }
            }
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
                navController = navController,
                token = token.value,
                context = localContext,
                adminCreateLessonViewModel = adminCreateLessonViewModel // Pass ViewModel here
            )
        }

//        composable(route = PagesEnum.UpdateLesson.name) {
//            AdminUpdateLesson(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(Color.White),
//                navController = navController,
//                token = token.value,
//                context = localContext,
//                adminUpdateLessonViewModel = adminUpdateLessonViewModel // Pass ViewModel here
//            )
//        }

        composable(route = "${PagesEnum.UpdateLesson.name}/{lessonId}") { backStackEntry ->
            val lessonId = backStackEntry.arguments?.getString("lessonId")?.toIntOrNull()
            if (lessonId != null) {
                AdminUpdateLesson(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    navController = navController,
                    token = token.value,
                    context = localContext,
                    adminUpdateLessonViewModel = adminUpdateLessonViewModel,
                    lessonId = lessonId // Pass the lessonId to AdminUpdateLesson
                )
            } else {
                // Handle error, e.g., show a fallback UI or log an error
            }
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

        composable(route = PagesEnum.Locations.name) {
            LocationPageView(navController)
        }

        composable(route = PagesEnum.Bukit.name) {
            Bukit(bukitViewModel, navController)
        }

        composable(route = PagesEnum.Lapangan.name) {
            Lapangan(lapanganViewModel, navController)
        }

        composable(route = PagesEnum.Gedung.name) {
            Gedung(gedungViewModel, navController)
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