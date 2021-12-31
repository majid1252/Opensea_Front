package com.mine.opensea

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

public fun Observable<Any>.applyAndroidSchedulers(): Observable<Any> {
    return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}
