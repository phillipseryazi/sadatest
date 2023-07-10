package com.example.sadatest.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.example.sadatest.R
import com.example.sadatest.domain.GitRepo
import com.example.sadatest.domain.Owner
import com.example.sadatest.ui.models.HomeScreenState
import com.example.sadatest.utils.TestTags
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun navbar_Is_Present() {
        composeTestRule.setContent {
            HomeScreen(
                state = HomeScreenState(),
                handleEvent = {}
            )
        }
        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation().targetContext.getString(R.string.text_trending)
        ).assertIsDisplayed()
    }

    @Test
    fun loader_Is_displayed() {
        composeTestRule.setContent {
            HomeScreen(
                state = HomeScreenState(
                    isLoading = true,
                    showError = false,
                    repos = emptyList()
                ),
                handleEvent = {}
            )
        }
        composeTestRule.onNodeWithTag(TestTags.SHIMMER_LIST_TEST_TAG).assertIsDisplayed()
    }

    @Test
    fun repoList_Is_Displayed() {
        composeTestRule.setContent {
            HomeScreen(
                state = HomeScreenState(
                    isLoading = false,
                    showError = false,
                    repos = listOf(
                        GitRepo(
                            owner = Owner(login = "test1", avatar_url = "sample1"),
                            name = "testRepo1",
                            description = "testDescription1",
                            stargazers_count = 5,
                            language = "Javascript"
                        ),
                        GitRepo(
                            owner = Owner(login = "test2", avatar_url = "sample2"),
                            name = "testRepo2",
                            description = "testDescription2",
                            stargazers_count = 10,
                            language = "Python"
                        )
                    )
                ),
                handleEvent = {}
            )
        }
        composeTestRule.onNodeWithTag(TestTags.REPO_LIST_TEST_TAG).assertIsDisplayed()
    }

    @Test
    fun errorScreen_Is_Displayed() {
        composeTestRule.setContent {
            HomeScreen(
                state = HomeScreenState(
                    isLoading = false,
                    showError = true,
                    repos = emptyList()
                ),
                handleEvent = {}
            )
        }
        composeTestRule.onNodeWithTag(TestTags.BUSY_ANIM_TEST_TAG).assertIsDisplayed()
    }

    @Test
    fun reply_Button_Is_Displayed() {
        composeTestRule.setContent {
            HomeScreen(
                state = HomeScreenState(
                    isLoading = false,
                    showError = true,
                    repos = emptyList()
                ),
                handleEvent = {}
            )
        }
        composeTestRule.onNodeWithTag(TestTags.RETRY_BTN_TEST_TAG).assertIsDisplayed()
    }

    @Test
    fun repoList_Is_Expanded_When_Clicked() {
        composeTestRule.setContent {
            HomeScreen(
                state = HomeScreenState(
                    isLoading = false,
                    showError = false,
                    repos = listOf(
                        GitRepo(
                            owner = Owner(login = "test1", avatar_url = "sample1"),
                            name = "testRepo1",
                            description = "testDescription1",
                            stargazers_count = 5,
                            language = "Javascript"
                        )
                    )
                ),
                handleEvent = {}
            )
        }

        composeTestRule.onNodeWithTag(TestTags.GIT_REPO_ITEM_TEST_TAG).performClick()
        composeTestRule.onNodeWithTag(TestTags.EXPANDED_CONTENT_TEST_TAG, useUnmergedTree = true).assertIsDisplayed()
    }
}
