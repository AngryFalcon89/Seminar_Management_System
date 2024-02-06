package com.example.seminarmanagementsystem.presentation.navGraph

sealed class Route(
    val route: String
) {
    object LoginScreen: Route(route = "loginScreen")
    object SignupScreen: Route(route = "signupScreen")
    object HomeScreen: Route(route = "homeScreen")
    object SearchScreen: Route(route = "searchScreen")
    object IssueScreen: Route(route = "issueScreen")
    object DetailsScreen: Route(route = "detailsScreen")
    object AddScreen: Route(route = "addScreen")
    object UpdateScreen: Route(route = "updateScreen")
    object IssueTheBookScreen: Route(route = "issueTheBookScreen")
    object ForgetPasswordScreen: Route(route = "forgetPasswordScreen")
    //subGraphs
    object AppStartNavigation: Route(route = "appStartNavigation")
    object BookNavigation: Route(route = "bookNavigation")
    object BookNavigatorScreen: Route(route = "bookNavigatorScreen")
}