package com.android.carousell.viewmodels

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.carousell.di.DaggerAppComponent
import com.android.carousell.models.NewsDo
import com.android.carousell.usecases.GetNewsUseCase
import com.android.carousell.utils.NetworkUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsViewModel : ViewModel(){

    @Inject
    lateinit var getNewsUseCase: GetNewsUseCase
    private var disposable = CompositeDisposable()
    private val mNewsObjects = MutableLiveData<List<NewsDo>>()
    val handleNewsData : MutableLiveData<List<NewsDo>> get() = mNewsObjects
    private var mError = MutableLiveData<Errors>()
    val handleError : MutableLiveData<Errors> get() = mError
    private var mLoading = MutableLiveData<Boolean>()
    val handleLoader : MutableLiveData<Boolean> get() = mLoading

    init {
        DaggerAppComponent.create().inject(this)
    }
    fun getNewsFromServer(){
        if(!NetworkUtils.isNetworkAvailable()){
            mError.value = Errors.NetworkError
            return
        }
        mLoading.value = true
        disposable.add(
            getNewsUseCase.getAction()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { newsDos, error ->
                    mLoading.value = false
                    error?.let {
                        Log.e("ERROR:", "${it.message}")
                        mError.value = Errors.ServerError
                        return@subscribe
                    }
                    mNewsObjects.value = newsDos
                }
        )
    }

    enum class Errors {
        NetworkError,
        ServerError
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}