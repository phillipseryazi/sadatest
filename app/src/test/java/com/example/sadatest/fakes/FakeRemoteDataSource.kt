package com.example.sadatest.fakes

import com.example.sadatest.data.GitRepoDTO
import com.example.sadatest.data.GitRepoResponse
import com.example.sadatest.data.IRemoteDataSource
import com.example.sadatest.data.OwnerDTO
import com.example.sadatest.utils.FetchResult

class FakeRemoteDataSource : IRemoteDataSource {
    override suspend fun searchUserRepositories(options: Map<String, String>): FetchResult<GitRepoResponse> {
        return FetchResult.OnSuccess(
            data = GitRepoResponse(
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
                    ),
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
            )
        )
    }
}