package com.android.carousell.usecases

import com.android.carousell.models.NewsDo
import com.android.carousell.reposotories.NewsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) :
    ReactiveUseCase.SingleNoParamUseCase<List<NewsDo>> {
    override fun getAction(): Single<List<NewsDo>> {
        return newsRepository.getNewsFromServer()
    }
}