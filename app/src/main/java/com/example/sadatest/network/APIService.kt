package com.example.sadatest.network

import com.example.sadatest.data.GitRepoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface APIService {
    @GET("/search/repositories")
    suspend fun searchUserRepositories(@QueryMap options: Map<String, String>): Response<GitRepoResponse>
}
