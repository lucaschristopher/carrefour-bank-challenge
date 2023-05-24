package com.example.carrefourbankchallenge.presentation.navigation

import com.example.carrefourbankchallenge.core.constants.DETAIL_SCREEN
import com.example.carrefourbankchallenge.core.constants.HOME_SCREEN
import com.example.carrefourbankchallenge.core.constants.SEARCH_SCREEN
import com.example.carrefourbankchallenge.core.constants.USER_ARG

sealed class Route(val route: String) {

    object Home : Route(HOME_SCREEN)

    object Search : Route(SEARCH_SCREEN)

    object Detail : Route("$DETAIL_SCREEN/{$USER_ARG}/") {
        fun createRoute(username: String) = "$DETAIL_SCREEN/$username/"
    }
}
