package com.example.sadatest.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sadatest.domain.IGitRepoRepository
import com.example.sadatest.ui.models.HomeEvent
import com.example.sadatest.ui.models.HomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GitRepoViewModel @Inject constructor(
    private val repo: IGitRepoRepository
) : ViewModel() {
    var homeScreenState = MutableStateFlow(HomeScreenState())
        private set

    fun handleEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.SearchGitRepos -> {
                searchGitRepos(event.options)
            }
        }
    }

    private fun searchGitRepos(options: Map<String, String>) {
        viewModelScope.launch {

        }
    }
}
