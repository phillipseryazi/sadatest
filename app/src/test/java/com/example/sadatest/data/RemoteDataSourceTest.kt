package com.example.sadatest.data

import com.example.sadatest.network.APIService
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection
import java.net.SocketTimeoutException

@OptIn(ExperimentalCoroutinesApi::class)
class RemoteDataSourceTest {

    private lateinit var repository: IRemoteDataSource
    private lateinit var apiService: APIService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/").toString())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)

        repository = RemoteDataSource(apiService)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `test success with no users 200`() = runTest {
        val repos = emptyList<GitReposDTO>()
        val expectedResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(Gson().toJson(repos))
        mockWebServer.enqueue(expectedResponse)

        val actualResponse = repository.searchUserRepositories(
            mapOf(
                "language" to "",
                "sort" to "stars"
            )
        )
        assertThat(actualResponse.getOrThrow()).hasSize(0)
    }

    @Test
    fun `test success with users 200`() = runTest {
        val repos = listOf(
            GitReposDTO(
                items = listOf(
                    GitRepoDTO(
                        owner = OwnerDTO(
                            login = "test-user1",
                            avatar_url = "https//:test_user1.com"
                        ),
                        name = "test-repo1",
                        description = "this is the first test repo",
                        stargazers_count = 4,
                        language = "Javascript"
                    )
                )
            ),
            GitReposDTO(
                items = listOf(
                    GitRepoDTO(
                        owner = OwnerDTO(
                            login = "test-user2",
                            avatar_url = "https//:test_user2.com"
                        ),
                        name = "test-repo2",
                        description = "this is the second test repo",
                        stargazers_count = 10,
                        language = "Python"
                    )
                )
            ),
        )

        val expectedResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(Gson().toJson(repos))
        mockWebServer.enqueue(expectedResponse)

        val actualResponse = repository.searchUserRepositories(
            mapOf(
                "language" to "",
                "sort" to "stars"
            )
        )
        assertThat(actualResponse.getOrThrow()).hasSize(2)

    }

    @Test
    fun `test failure with timeout exception`() = runTest {
        val expectedResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_CLIENT_TIMEOUT)
        mockWebServer.enqueue(expectedResponse)

        val actualResponse = repository.searchUserRepositories(
            mapOf(
                "language" to "",
                "sort" to "stars"
            )
        )
        assertThat(actualResponse.exceptionOrNull()).isInstanceOf(SocketTimeoutException::class.java)
    }
}
