package com.mine.opensea

import android.widget.ImageView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import android.graphics.drawable.BitmapDrawable

import android.graphics.Bitmap
import androidx.palette.graphics.Palette


/**
 * simply adding necessary android schedulers for [Observable]
 */
fun Observable<Any>.applyAndroidSchedulers(): Observable<Any> {
    return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

/**
 * getting dominant color accent from [ImageView]
 */
fun ImageView.getDominantColor(): Int {
    val bitmap = (drawable as BitmapDrawable).bitmap
    var vibrant: Int = R.color.accent_color
    Palette.from(bitmap).generate { palette ->
        vibrant = palette?.getVibrantColor(0x000000)!!
    }
    return vibrant
}
