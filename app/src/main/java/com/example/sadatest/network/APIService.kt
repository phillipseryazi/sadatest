package com.example.sadatest.network

import com.example.sadatest.data.UserRepositoryDTO
import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    @GET("/search/repositories?q=language=+sort:stars")
    suspend fun searchUserRepositories(): Response<List<UserRepositoryDTO>>
}
