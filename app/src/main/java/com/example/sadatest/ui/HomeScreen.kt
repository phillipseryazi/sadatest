package com.example.sadatest.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.sadatest.domain.GitRepo
import com.example.sadatest.ui.elements.BusyAnim
import com.example.sadatest.ui.elements.GitRepoItem
import com.example.sadatest.ui.elements.SadaNavbar
import com.example.sadatest.ui.elements.ShimmerList
import com.example.sadatest.ui.models.HomeEvent
import com.example.sadatest.ui.models.HomeScreenState
import com.example.sadatest.utils.TestTags

@Composable
fun HomeScreen(
    state: HomeScreenState,
    handleEvent: (event: HomeEvent) -> Unit,
) {
    LaunchedEffect(key1 = null, block = {
        handleEvent(
            HomeEvent.SearchGitRepos(
                mapOf(
                    "q" to "language=+sort:stars"
                )
            )
        )
    })
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        SadaNavbar()
        when {
            state.showError -> {
                BusyAnim(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .align(Alignment.CenterHorizontally)
                        .testTag(TestTags.BUSY_ANIM_TEST_TAG),
                    handleEvent = {
                        handleEvent(
                            HomeEvent.RetrySearchGitRepos(
                                mapOf(
                                    "q" to "language=+sort:stars"
                                )
                            )
                        )
                    }
                )
            }

            state.isLoading -> {
                ShimmerList()
            }

            else -> {
                HomeScreenList(state.repos)
            }
        }
    }
}

@Composable
fun HomeScreenList(repoList: List<GitRepo>) {
    var expandedItem by remember {
        mutableStateOf(-1)
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .testTag(TestTags.REPO_LIST_TEST_TAG)
    ) {
        items(repoList.size) { idx ->
            GitRepoItem(
                expanded = expandedItem == idx,
                repo = repoList[idx],
                onItemClicked = {
                    expandedItem = if (expandedItem == idx) {
                        -1
                    } else {
                        idx
                    }
                }
            )
        }
    }
}
