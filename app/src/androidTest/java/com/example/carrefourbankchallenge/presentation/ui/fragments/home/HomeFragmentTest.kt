package com.example.carrefourbankchallenge.presentation.ui.fragments.home

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.carrefourbankchallenge.presentation.navigation.NavGraph
import com.example.carrefourbankchallenge.presentation.navigation.Route
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            NavGraph(navHostController = navController)
        }
    }

    @Test
    fun appBarShouldBeDisplayedInHomeFragment() {
        val title = "GitHub Users"
        composeTestRule.onNodeWithText(title).assertIsDisplayed()
    }

    @Test
    fun checkButtonNavigationToSearchFragmentIsDisplayed() {
        val contentDescriptionFABSearch = "search button"
        composeTestRule
            .onNodeWithContentDescription(contentDescriptionFABSearch)
            .assertIsDisplayed()

    }

    @Test
    fun checkNavigationToSearchFragment() {
        val contentDescriptionFABSearch = "search button"

        composeTestRule
            .onNodeWithContentDescription(contentDescriptionFABSearch)
            .performClick()

        val route = navController.currentBackStackEntry?.destination?.route

        assertEquals(route, Route.Search.route)
    }
}