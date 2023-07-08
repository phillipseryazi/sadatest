package com.example.sadatest.domain

import com.example.sadatest.data.IRemoteDataSource
import com.example.sadatest.data.toGitRepo
import com.example.sadatest.utils.FetchResult
import javax.inject.Inject

class GitRepoRepository @Inject constructor(
    private val dataSource: IRemoteDataSource
) : IGitRepoRepository {
    override suspend fun searchUserRepositories(options: Map<String, String>): FetchResult<List<GitRepo>> {
        return when (val result = dataSource.searchUserRepositories(options)) {
            is FetchResult.OnSuccess -> {
                val items = result.data.items.map {
                    it.toGitRepo()
                }
                FetchResult.OnSuccess(items)
            }

            is FetchResult.OnFailure -> {
                FetchResult.OnFailure(result.error)
            }
        }
    }
}
