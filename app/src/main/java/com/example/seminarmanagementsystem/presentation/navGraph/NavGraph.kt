package com.example.seminarmanagementsystem.presentation.navGraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.seminarmanagementsystem.presentation.bookNavigator.BookNavigator
import com.example.seminarmanagementsystem.presentation.userAuth.forgetPassword.ForgetPasswordScreen
import com.example.seminarmanagementsystem.presentation.userAuth.forgetPassword.ForgetPasswordViewModel
import com.example.seminarmanagementsystem.presentation.userAuth.login.LoginScreen
import com.example.seminarmanagementsystem.presentation.userAuth.login.LoginViewModel

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.LoginScreen.route
        ) {
            composable(
                route = Route.LoginScreen.route
            ) {
                val viewModel: LoginViewModel = hiltViewModel()
                LoginScreen(
                    viewModel = viewModel,
                    onLoginClicked = {
                        viewModel.login()
                    },
                    onSkipClicked = {
                        navController.navigate(Route.BookNavigation.route){
                            viewModel.saveAppEntry()
                            popUpTo(Route.AppStartNavigation.route) {
                                inclusive = true
                            }
                        }
                    },
                    onForgetClicked = {
                        navController.navigate(Route.ForgetPasswordScreen.route)
                    }
                )
            }
            composable(route = Route.ForgetPasswordScreen.route){
                val viewModel: ForgetPasswordViewModel = hiltViewModel()
                ForgetPasswordScreen(
                    viewModel = viewModel,
                    onChangePassword = {
                        viewModel.generateOtp()
                    },
                    onOtpEnter = {
                        viewModel.verifyOtp()
                    }
                )
            }
        }
        navigation(
            route = Route.BookNavigation.route,
            startDestination = Route.BookNavigatorScreen.route
        ) {
            composable(
                route = Route.BookNavigatorScreen.route
            ) {
                BookNavigator()
            }
        }
    }
}