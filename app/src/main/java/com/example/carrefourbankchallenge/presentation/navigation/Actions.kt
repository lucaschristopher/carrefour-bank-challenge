package com.example.carrefourbankchallenge.presentation.navigation

import androidx.navigation.NavHostController

class Actions(navHostController: NavHostController) {

    val navigateBack: () -> Unit = {
        navHostController.navigateUp()
    }

    val navigateToSearchUser: () -> Unit = {
        navHostController.navigate(Route.Search.route)
    }

    val openUserDetail: (String) -> Unit = { username ->
        navHostController.navigate(
            Route.Detail.createRoute(username)
        )
    }
}