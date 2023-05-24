package com.example.carrefourbankchallenge.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.carrefourbankchallenge.core.constants.EMPTY_STRING
import com.example.carrefourbankchallenge.core.constants.USER_ARG
import com.example.carrefourbankchallenge.presentation.ui.fragments.detail.UserDetailFragment
import com.example.carrefourbankchallenge.presentation.ui.fragments.home.HomeFragment
import com.example.carrefourbankchallenge.presentation.ui.fragments.search.SearchFragment

@Composable
fun NavGraph(navHostController: NavHostController) {

    val actions = remember(navHostController) { Actions(navHostController) }

    NavHost(
        navController = navHostController,
        startDestination = Route.Home.route
    ) {
        composable(route = Route.Home.route) {
            HomeFragment(
                actionSearchUser = actions.navigateToSearchUser,
                actionOpenUserDetails = actions.openUserDetail
            )
        }

        composable(route = Route.Search.route) {
            SearchFragment(
                actionNavigateBack = actions.navigateBack,
                actionOpenUserDetails = actions.openUserDetail,
            )
        }

        composable(
            route = Route.Detail.route,
            arguments = listOf(
                navArgument(USER_ARG) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val username = backStackEntry.arguments?.getString(USER_ARG) ?: EMPTY_STRING

            UserDetailFragment(
                actionNavigateBack = actions.navigateBack,
                username = username
            )
        }
    }
}