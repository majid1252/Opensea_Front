package com.mine.opensea

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.graphics.*
import android.widget.ImageView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import android.graphics.drawable.BitmapDrawable

import android.view.View
import androidx.palette.graphics.Palette
import android.util.Log

import android.graphics.Bitmap
import androidx.palette.graphics.Palette.Swatch
import java.util.*
import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.annotation.SuppressLint

import androidx.core.content.ContextCompat

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
    return (drawable as BitmapDrawable).bitmap.getDominantColor()
}

/**
 * get [Bitmap] dominant color
 */
fun Bitmap.getDominantColorOfSwatch(): Int {
    val swatchesTemp = Palette.from(this).generate().swatches
    val swatches: List<Swatch> = ArrayList(swatchesTemp)
    Collections.sort(swatches,
                     Comparator<Swatch> { swatch1, swatch2 -> swatch2.population - swatch1.population })
    return if (swatches.isNotEmpty()) swatches[0].rgb else 100000
}

fun Bitmap.getDominantColor(): Int {
    val builder = Palette.Builder(this)
        .setRegion(0, 0, this.width, this.height)
    val defaultValue = 0xFFFFFF
    val p = builder.generate()
    return p.getDominantColor(defaultValue)
}

fun Bitmap.getUpperSideDominantColor(): Int {
    val builder = Palette.Builder(this)
        .setRegion(0, 0, this.width, this.height / 2)
    val defaultValue = 0xFFFFFF
    val p = builder.generate()
    return p.getDominantColor(defaultValue)
}

fun Bitmap.getLowerSideDominantColor(): Int {
    val defaultValue = 0xFFFFFF
    val builder = Palette.Builder(this)
        .setRegion(0, this.height / 2, this.width, this.height)
    return builder.generate().getDominantColor(defaultValue)
}

fun Bitmap.getDominantGradient(): Bitmap? {
    var topColor = 0
    var bottomColor = 0
    topColor = getUpperSideDominantColor()
    bottomColor = getLowerSideDominantColor()
    var topHex = Integer.toHexString(topColor)
    topHex = topHex.trim { it <= ' ' }
    topHex = "#$topHex"
    var bottomHex = Integer.toHexString(bottomColor)
    bottomHex = bottomHex.trim { it <= ' ' }
    bottomHex = "#$bottomHex"
    Log.e("color ", topHex)
    Log.e("color ", bottomHex)
    val colors = intArrayOf(Color.parseColor(topHex), Color.parseColor(bottomHex))
    //int[]colors = new int[]{ Color.GREEN,Color.BLACK};
    val mShader: Shader = LinearGradient(
        0f, 0f, width / 2f, height / 2f, colors,
        null, Shader.TileMode.CLAMP
    )
    val m = Matrix()
    m.setRotate(90f)
    mShader.setLocalMatrix(m)
    val paint = Paint()
    paint.shader = mShader
    val resultBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(resultBitmap)
    canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
    //canvas.drawRect(0,0,640,1137,paint);
    val matrix = Matrix()
    canvas.drawBitmap(resultBitmap, matrix, paint)
    return resultBitmap
}

/**
 * Color animator for [View] background
 */
@SuppressLint("Recycle")
fun View.animateColor(fromColor: Int = solidColor, toColor: Int, duration: Long) {
    val colorEnd = Color.BLUE
    val colorAnim: ValueAnimator =
        ObjectAnimator.ofInt(this, "backgroundColor", fromColor, colorEnd)
    colorAnim.duration = 1000
    colorAnim.setEvaluator(ArgbEvaluator())
    colorAnim.repeatCount = 0
    colorAnim.start()
}