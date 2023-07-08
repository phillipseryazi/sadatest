package com.example.sadatest.data

import com.google.gson.annotations.SerializedName

data class GitReposDTO(
    @SerializedName("items")
    val items: List<GitRepoDTO>
)

data class GitRepoDTO(
    @SerializedName("owner")
    val owner: OwnerDTO,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("stargazers_count")
    val stargazers_count: Int,
    @SerializedName("language")
    val language: String
)

data class OwnerDTO(
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatar_url: String
)
