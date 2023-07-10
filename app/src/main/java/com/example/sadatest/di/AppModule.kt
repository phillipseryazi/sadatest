package com.example.sadatest.di

import com.example.sadatest.data.IRemoteDataSource
import com.example.sadatest.data.RemoteDataSource
import com.example.sadatest.domain.GitRepoRepository
import com.example.sadatest.domain.IGitRepoRepository
import com.example.sadatest.network.APIService
import com.example.sadatest.utils.BASE_URL
import com.example.sadatest.utils.IDispatcherProvider
import com.example.sadatest.utils.MyDispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Singleton
    @Provides
    fun providesHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(client: OkHttpClient): APIService {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
        return retrofit.create(APIService::class.java)
    }

    @Singleton
    @Provides
    fun providesRemoteDataSource(apiService: APIService) =
        RemoteDataSource(apiService) as IRemoteDataSource

    @Singleton
    @Provides
    fun providesMainRepository(remoteDataSource: IRemoteDataSource) =
        GitRepoRepository(remoteDataSource) as IGitRepoRepository

    @Singleton
    @Provides
    fun providesMyDispatcherProvider() = MyDispatcherProvider() as IDispatcherProvider

}
