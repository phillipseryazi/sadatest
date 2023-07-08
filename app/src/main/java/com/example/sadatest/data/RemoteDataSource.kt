package com.example.sadatest.data

import com.example.sadatest.network.APIService
import com.example.sadatest.utils.executeApiCall
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: APIService) : IRemoteDataSource {
    override suspend fun searchUserRepositories(options: Map<String, String>): Result<GitRepoResponse> {
        return executeApiCall { apiService.searchUserRepositories(options) }
    }
}
