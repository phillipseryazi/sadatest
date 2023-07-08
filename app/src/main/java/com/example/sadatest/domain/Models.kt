package com.example.sadatest.domain

data class GitRepo(
    val owner: Owner,
    val name: String,
    val description: String,
    val stargazers_count: Int,
    val language: String
)

data class Owner(
    val login: String,
    val avatar_url: String
)
