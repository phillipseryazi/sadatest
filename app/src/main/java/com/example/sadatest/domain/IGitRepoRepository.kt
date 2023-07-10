package com.example.sadatest.domain

import com.example.sadatest.utils.FetchResult

interface IGitRepoRepository {
    suspend fun searchUserRepositories(options: Map<String, String>): FetchResult<List<GitRepo>>
}
