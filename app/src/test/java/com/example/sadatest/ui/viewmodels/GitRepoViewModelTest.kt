package com.example.sadatest.ui.viewmodels

import com.example.sadatest.MainDispatcherRule
import com.example.sadatest.TestDispatcherProvider
import com.example.sadatest.data.IRemoteDataSource
import com.example.sadatest.domain.IGitRepoRepository
import com.example.sadatest.fakes.FakeGitRepoRepository
import com.example.sadatest.fakes.FakeRemoteDataSourceFailure
import com.example.sadatest.fakes.FakeRemoteDataSourceSuccess
import com.example.sadatest.ui.models.HomeEvent
import com.example.sadatest.utils.IDispatcherProvider
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GitRepoViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: GitRepoViewModel
    lateinit var repository: IGitRepoRepository
    private lateinit var successDataSource: IRemoteDataSource
    lateinit var failDataSource: IRemoteDataSource
    private lateinit var dispatcher: IDispatcherProvider

    @Before
    fun setUp() {
        successDataSource = FakeRemoteDataSourceSuccess()
        failDataSource = FakeRemoteDataSourceFailure()
        dispatcher = TestDispatcherProvider()
    }

    @Test
    fun `handleEvent with HomeEvent_SearchGitRepos should update homeScreenState with successful search`() =
        runTest {
            repository = FakeGitRepoRepository(successDataSource)
            viewModel = GitRepoViewModel(repository, dispatcher)

            val options = mapOf("q" to "example")
            viewModel.handleEvent(HomeEvent.SearchGitRepos(options))
            assertThat(viewModel.homeScreenState.value.repos).hasSize(2)
            assertThat(viewModel.homeScreenState.value.repos[0].language).isEqualTo("Javascript")
        }

    @Test
    fun `handleEvent with HomeEvent_RetrySearchGitRepos should update homeScreenState with retry search`() =
        runTest {
            repository = FakeGitRepoRepository(successDataSource)
            viewModel = GitRepoViewModel(repository, dispatcher)

            val options = mapOf("q" to "example")
            viewModel.handleEvent(HomeEvent.RetrySearchGitRepos(options))
            assertThat(viewModel.homeScreenState.value.repos).hasSize(2)
            assertThat(viewModel.homeScreenState.value.repos[1].language).isEqualTo("Python")
        }


    @Test
    fun `searchGitRepos should update homeScreenState with error`() = runTest {
        repository = FakeGitRepoRepository(failDataSource)
        viewModel = GitRepoViewModel(repository, dispatcher)

        val options = mapOf("q" to "example")
        viewModel.handleEvent(HomeEvent.SearchGitRepos(options))
        assertThat(viewModel.homeScreenState.value.repos).hasSize(0)
    }
}
