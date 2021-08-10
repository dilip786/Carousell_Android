package com.android.carousell.di

import com.android.carousell.reposotories.NewsRepository
import com.android.carousell.usecases.GetNewsUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCasesModule {

    @Singleton
    @Provides
    fun providesNewsUseCase(newsRepository: NewsRepository) :GetNewsUseCase {
        return GetNewsUseCase(newsRepository)
    }
}