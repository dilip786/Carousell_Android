package com.android.carousell.di

import com.android.carousell.ui.NewsActivity
import com.android.carousell.viewmodels.NewsViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, UseCasesModule::class])
interface AppComponent {
    fun inject (newsActivity: NewsActivity)
    fun inject (newsViewModel: NewsViewModel)
}