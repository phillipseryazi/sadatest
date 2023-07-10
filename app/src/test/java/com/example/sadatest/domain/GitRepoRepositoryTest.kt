package com.example.sadatest.domain

import com.example.sadatest.fakes.FakeRemoteDataSourceSuccess
import com.example.sadatest.utils.FetchResult
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GitRepoRepositoryTest {

    private val remoteDataSource = FakeRemoteDataSourceSuccess()
    lateinit var repository: IGitRepoRepository


    @Before
    fun setUp() {
        repository = GitRepoRepository(remoteDataSource)
    }

    @Test
    fun `test fetch and transform users in repository`() = runTest {
        val response = repository.searchUserRepositories(
            mapOf(
                "language" to "",
                "sort" to "stars"
            )
        )

        val result = response as FetchResult.OnSuccess
        assertThat(result.data.size).isEqualTo(2)
    }
}
