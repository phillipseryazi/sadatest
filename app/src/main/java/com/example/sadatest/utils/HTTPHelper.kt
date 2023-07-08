package com.example.sadatest.utils

import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <T : Any> executeApiCall(
    execute: suspend () -> Response<T>
): FetchResult<T> {
    return try {
        val response = execute()
        if (response.isSuccessful) {
            FetchResult.OnSuccess(response.body()!!)
        } else {
            FetchResult.OnFailure(IOException(response.message()))
        }
    } catch (exc: HttpException) {
        FetchResult.OnFailure(exc)
    } catch (exc: IOException) {
        FetchResult.OnFailure(exc)
    } catch (exc: UnknownHostException) {
        FetchResult.OnFailure(exc)
    } catch (exc: SocketTimeoutException) {
        FetchResult.OnFailure(exc)
    } catch (exc: NullPointerException) {
        FetchResult.OnFailure(exc)
    }
}
