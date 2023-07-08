package com.example.sadatest.data

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RemoteDataSourceTest {


    @Test
    fun `test success but no users`() = runTest {

    }

    @Test
    fun `test success with users`() = runTest {

    }

    @Test
    fun `test failure with connection exception`() = runTest {

    }
}