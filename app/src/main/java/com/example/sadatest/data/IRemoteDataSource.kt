package com.example.sadatest.data

import com.example.sadatest.utils.FetchResult

interface IRemoteDataSource {
    suspend fun searchUserRepositories(options: Map<String, String>): FetchResult<GitRepoResponse>
}
