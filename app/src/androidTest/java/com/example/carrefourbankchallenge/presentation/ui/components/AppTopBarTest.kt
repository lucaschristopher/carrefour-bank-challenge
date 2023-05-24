package com.example.carrefourbankchallenge.presentation.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.carrefourbankchallenge.presentation.ui.theme.CarrefourBankChallengeTheme
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)  // To indicate that we've to run it with AndroidJUnit runner
class AppTopBarTest {

    // Compose rule is required to get access to the composable component
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun givenTitleShouldBeDisplayedInHomeAppBar() {

        val title = "GitHub Users"

        composeTestRule.setContent {
            CarrefourBankChallengeTheme {
                AppTopBar(title = title, showBackButton = false, navigateBack = {})
            }
        }
        composeTestRule.onNodeWithText(title).assertIsDisplayed()
    }

    @Test
    fun backButtonShouldBeDisplayedInHomeAppBar() {
        val title = "GitHub Users"
        val backButtonDescription = "back button"

        composeTestRule.setContent {
            CarrefourBankChallengeTheme {
                AppTopBar(title = title, showBackButton = true, navigateBack = {})
            }
        }
        composeTestRule.onNodeWithContentDescription(backButtonDescription).assertIsDisplayed()
    }

    @Test
    fun appBarIconsShouldBeClickableInHomeAppBar() {
        val title = "GitHub Users"
        val backButtonDescription = "back button"
        var testBackButtonClick = false

        composeTestRule.setContent {
            CarrefourBankChallengeTheme {
                AppTopBar(
                    title = title,
                    showBackButton = true,
                    navigateBack = { testBackButtonClick = true }
                )
            }
        }
        composeTestRule.onNodeWithContentDescription(backButtonDescription).performClick()
        assertTrue(testBackButtonClick)
    }
}