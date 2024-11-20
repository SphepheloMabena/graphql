package com.example.graphql

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.example.graphql.data.SimpleCountry
import com.example.graphql.presentation.composable.CountriesScreen
import com.example.graphql.presentation.viewModels.CountriesViewModel
import org.junit.Rule
import org.junit.Test

class CountriesScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testLoadingState_ShowsCircularProgressIndicator() {
        // Set up the state where the screen is loading
        val loadingState =  CountriesViewModel.CountriesState(
            isLoading = true,
            countries = emptyList()
        )
        composeTestRule.setContent {
            CountriesScreen(state = loadingState)
        }

        // Check that CircularProgressIndicator is displayed
        composeTestRule.onNodeWithTag("ProgressIndicator")
            .assertIsDisplayed()
    }

    @Test
    fun testLoadedState_ShowsCountryList() {
        // Set up the state with sample country data
        val loadedState = CountriesViewModel.CountriesState(
            isLoading = false,
            countries = listOf(
                SimpleCountry("🇫🇷", "France", "Paris", ""),
                SimpleCountry("🇯🇵", "Japan", "Tokyo", "")
            )
        )

        composeTestRule.setContent {
            CountriesScreen(state = loadedState)
        }

        // Check that the list of countries is displayed
        composeTestRule.onNodeWithText("France")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("Paris")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("Japan")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("Tokyo")
            .assertIsDisplayed()
    }
}
