package com.example.sadatest.ui.models

import com.example.sadatest.domain.GitRepo

sealed class HomeEvent {
    data class SearchGitRepos(val options: Map<String, String>) : HomeEvent()
}

data class HomeScreenState(
    val repos: List<GitRepo> = emptyList(),
    val isLoading: Boolean = true,
    val showError: Boolean = false
)
