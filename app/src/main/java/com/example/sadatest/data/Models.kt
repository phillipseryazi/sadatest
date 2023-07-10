package com.example.sadatest.data

import com.example.sadatest.domain.GitRepo
import com.example.sadatest.domain.Owner
import com.google.gson.annotations.SerializedName

data class GitRepoResponse(
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
    val language: String?
)

data class OwnerDTO(
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatar_url: String
)

fun GitRepoResponse.getRepoList(): List<GitRepo> {
    return items.map {
        it.toGitRepo()
    }
}

fun GitRepoDTO.toGitRepo(): GitRepo {
    return GitRepo(
        owner = Owner(login = owner.login, avatar_url = owner.avatar_url),
        name = name,
        description = description,
        stargazers_count = stargazers_count,
        language = language ?: "null"
    )
}
