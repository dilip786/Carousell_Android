package com.android.carousell.usecases

import io.reactivex.Flowable
import io.reactivex.Single

interface ReactiveUseCase {

    interface SingleUseCase<Params, Result> {
        fun getAction(params: Params): Single<Result>
    }

    interface SingleNoParamUseCase<Result> {
        fun getAction(): Single<Result>
    }

    interface FlowableUseCase<Params, Result> {
        fun getAction(params: Params): Flowable<Result>
    }

    interface FlowableNoParamUseCase<Result> {
        fun getAction(): Flowable<Result>
    }
}