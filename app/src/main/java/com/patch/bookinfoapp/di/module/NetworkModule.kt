package com.patch.bookinfoapp.di.module

import androidx.databinding.library.BuildConfig
import com.patch.bookinfoapp.common.consts.NetworkConst
import com.patch.bookinfoapp.data.api.RemoteBookApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {
    const val NETWORK_TIME_OUT: Long = 5

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder().apply {
                header("Authorization", NetworkConst.REST_API_KEY)
            }.build()
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun createClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(NETWORK_TIME_OUT, TimeUnit.SECONDS)
            readTimeout(NETWORK_TIME_OUT, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) {
                addInterceptor(
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                )
            }
            addInterceptor(interceptor)
        }.build()
    }

    @Provides
    fun provideRemoteBookApi(okHttpClient: OkHttpClient): RemoteBookApi {
        return Retrofit.Builder()
            .baseUrl(NetworkConst.KAKAO_BOOK_API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RemoteBookApi::class.java)
    }
}