package com.example.sadatest.data

interface IRemoteDataSource {
    suspend fun searchUserRepositories(): Result<List<UserRepositoryDTO>>
}
