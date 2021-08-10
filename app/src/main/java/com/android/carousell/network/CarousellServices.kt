package com.android.carousell.network

import com.android.carousell.models.NewsDo
import io.reactivex.Single
import retrofit2.http.GET

interface CarousellServices {
    @GET("android/carousell_news.json")
    fun getCarousellNews(): Single<List<NewsDo>>
}