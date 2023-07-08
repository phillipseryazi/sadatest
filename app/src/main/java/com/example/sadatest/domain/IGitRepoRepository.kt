package com.example.sadatest.domain

interface IGitRepoRepository {
    suspend fun searchUserRepositories(options: Map<String, String>): List<GitRepo>
}