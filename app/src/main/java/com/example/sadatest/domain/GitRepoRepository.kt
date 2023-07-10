package com.example.sadatest.domain

import android.util.Log
import com.example.sadatest.data.IRemoteDataSource
import com.example.sadatest.data.getRepoList
import com.example.sadatest.utils.FetchResult
import javax.inject.Inject

class GitRepoRepository @Inject constructor(
    private val dataSource: IRemoteDataSource
) : IGitRepoRepository {
    override suspend fun searchUserRepositories(options: Map<String, String>): FetchResult<List<GitRepo>> {
        return when (val result = dataSource.searchUserRepositories(options)) {
            is FetchResult.OnSuccess -> {
                Log.d("executeApiCall:", "${result.data}")
                val items = result.data.getRepoList()
                FetchResult.OnSuccess(items)
            }

            is FetchResult.OnFailure -> {
                FetchResult.OnFailure(result.error)
            }
        }
    }
}
