package com.example.sadatest.data

interface IRemoteDataSource {
    suspend fun searchUserRepositories(options: Map<String, String>): Result<GitRepoResponse>
}
