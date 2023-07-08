package com.example.sadatest.domain

import com.example.sadatest.data.IRemoteDataSource
import com.example.sadatest.data.toGitRepo
import javax.inject.Inject

class GitRepoRepository @Inject constructor(
    private val dataSource: IRemoteDataSource
) : IGitRepoRepository {
    override suspend fun searchUserRepositories(options: Map<String, String>): List<GitRepo> {
        val result = dataSource.searchUserRepositories(options)

        return listOf()
    }
}
