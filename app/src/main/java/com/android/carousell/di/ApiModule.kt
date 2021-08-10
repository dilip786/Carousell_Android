package com.android.carousell.di

import com.android.carousell.network.CarousellServices
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {
    private val baseURL = "https://storage.googleapis.com/carousell-interview-assets/"

    @Singleton
    @Provides
    fun providesHttpClient(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
    }

    @Singleton
    @Provides
    fun providesCarousellApiService(client: Retrofit) : CarousellServices {
        return client.create(CarousellServices::class.java)
    }
}