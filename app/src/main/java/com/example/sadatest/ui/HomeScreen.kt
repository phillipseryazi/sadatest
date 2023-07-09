package com.example.sadatest.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.sadatest.domain.GitRepo
import com.example.sadatest.ui.elements.BusyAnim
import com.example.sadatest.ui.elements.SadaNavbar
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
                    "language" to "",
                    "sort" to "stars"
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
                BusyAnim(handleEvent)
            }

            state.isLoading -> {

            }

            else -> {
                HomeScreenList(state.repos)
            }
        }
    }
}

@Composable
fun HomeScreenList(repoList: List<GitRepo>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .testTag(TestTags.REPO_LIST_TEST_TAG)
    ) {
        items(repoList.size) { idx ->
            GitRepoItem(repo = repoList[idx])
        }
    }
}
