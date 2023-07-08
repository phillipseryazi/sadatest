package com.example.sadatest.data

import com.example.sadatest.network.APIService

class RemoteDataSource(private val apiService: APIService) : IRemoteDataSource {
    override suspend fun searchUserRepositories(): Result<List<UserRepositoryDTO>> {
        TODO("Not yet implemented")
    }
}
