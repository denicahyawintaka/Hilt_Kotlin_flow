package com.example.myapplication.module

import com.example.myapplication.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        const val BASED_URL = "https://api.themoviedb.org/3/"
    }

    @Provides
    fun provideNetworkClient(
        okHttpClient: OkHttpClient
    ): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASED_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build().create(ApiService::class.java)
    }

    @Provides
    fun provideOkHttpClient(
        okHttpClientBuilder: OkHttpClient.Builder
    ): OkHttpClient {
        return okHttpClientBuilder.build()
    }

    @Provides
    fun providesClient(): OkHttpClient.Builder {
        return OkHttpClient.Builder().addInterceptor { chain ->
            val request = chain.request().newBuilder()
            val originalHttpUrl = chain.request().url
            val url =
                originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", "eb7fda7d2094a7d74a933e33ed24e7d4")
                    .build()
            request.url(url)
            return@addInterceptor chain.proceed(request.build())
        }
    }
}
