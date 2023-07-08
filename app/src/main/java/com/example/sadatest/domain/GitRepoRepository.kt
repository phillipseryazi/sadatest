package com.example.sadatest.domain

import com.example.sadatest.data.IRemoteDataSource
import javax.inject.Inject

class GitRepoRepository @Inject constructor(
    private val dataSource: IRemoteDataSource
) : IGitRepoRepository {

}
