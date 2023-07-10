package com.example.sadatest.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sadatest.domain.IGitRepoRepository
import com.example.sadatest.ui.models.HomeEvent
import com.example.sadatest.ui.models.HomeScreenState
import com.example.sadatest.utils.FetchResult
import com.example.sadatest.utils.IDispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GitRepoViewModel @Inject constructor(
    private val repo: IGitRepoRepository,
    private val dispatcher: IDispatcherProvider
) : ViewModel() {
    var homeScreenState = MutableStateFlow(HomeScreenState())
        private set

    fun handleEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.SearchGitRepos -> {
                searchGitRepos(event.options)
            }

            is HomeEvent.RetrySearchGitRepos -> {
                retrySearchGitRepos(event.options)
            }
        }
    }

    private fun searchGitRepos(options: Map<String, String>) {
        viewModelScope.launch(dispatcher.io) {
            when (val result = repo.searchUserRepositories(options)) {
                is FetchResult.OnSuccess -> {
                    homeScreenState.update { state ->
                        state.copy(
                            isLoading = false,
                            showError = false,
                            repos = result.data
                        )
                    }
                }

                is FetchResult.OnFailure -> {
                    Log.d("searchGitRepos", result.error?.stackTraceToString() ?: "")
                    homeScreenState.update { state ->
                        state.copy(
                            isLoading = false,
                            showError = true,
                            repos = emptyList()
                        )
                    }
                }
            }
        }
    }

    private fun retrySearchGitRepos(options: Map<String, String>) {
        viewModelScope.launch(dispatcher.io) {
            homeScreenState.update { state ->
                state.copy(
                    isLoading = true,
                    showError = false,
                    repos = emptyList()
                )
            }

            searchGitRepos(options)
        }
    }
}
