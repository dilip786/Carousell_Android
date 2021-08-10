package com.android.carousell.reposotories

import com.android.carousell.network.CarousellServices
import com.android.carousell.models.NewsDo
import io.reactivex.Single
import javax.inject.Inject

class NewsRepository @Inject constructor(private val carousellServices: CarousellServices) {
    fun getNewsFromServer(): Single<List<NewsDo>> {
        return carousellServices.getCarousellNews()
    }
}