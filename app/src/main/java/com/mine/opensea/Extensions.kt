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
import android.annotation.SuppressLint
import android.content.Context
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.view.ViewTreeObserver

import androidx.core.content.ContextCompat
import com.mine.opensea.ExtFunctions.getRandomDrawable
import com.mine.opensea.OpenseaApplication.Companion.context
import kotlin.random.Random

/**
 * Find extension static method here
 */
object ExtFunctions {
    fun getRandomDrawable(): Int {
        val res = listOf(
            R.drawable.cool_sky,
            R.drawable.crystal_clear,
            R.drawable.kye_meh,
            R.drawable.mega_tron,
            R.drawable.sublime_light,
            R.drawable.sulphur,
            R.drawable.summer,
        )
        return res[Random.nextInt(0, res.size - 1)]
    }
}

/**
 * Simply add necessary android schedulers for [Observable]
 */
fun Observable<Any>.applyAndroidSchedulers(): Observable<Any> {
    return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

/**
 * Get dominant color from [ImageView]
 */
fun ImageView.getDominantColor(): Int {
    return (drawable as BitmapDrawable).bitmap.getDominantColor()
}

/**
 * Get [Bitmap] dominant color
 */
fun Bitmap.getDominantColorOfSwatch(): Int {
    val swatchesTemp = Palette.from(this).generate().swatches
    val swatches: List<Swatch> = ArrayList(swatchesTemp)
    Collections.sort(swatches,
                     Comparator<Swatch> { swatch1, swatch2 -> swatch2.population - swatch1.population })
    return if (swatches.isNotEmpty()) swatches[0].rgb else 100000
}

fun Bitmap.getPaletteOf(
        left: Int = 0,
        top: Int = 0,
        right: Int = 0,
        bottom: Int = 0
): Palette {
    val builder = Palette.Builder(this)
        .setRegion(left, top, right, bottom)
    return builder.generate()
}

// extension function to blur a bitmap
fun Bitmap.blur(context: Context, radius: Float = 10F): Bitmap? {
    val bitmap = copy(config, true)

    RenderScript.create(context).apply {
        val input = Allocation.createFromBitmap(this, this@blur)
        val output = Allocation.createFromBitmap(this, this@blur)

        ScriptIntrinsicBlur.create(this, Element.U8_4(this)).apply {
            setInput(input)
            // Set the radius of the Blur. Supported range 0 < radius <= 25
            setRadius(radius)
            forEach(output)

            output.copyTo(bitmap)
            destroy()
        }
    }
    return bitmap
}

fun Bitmap.blur(iterationsCount: Int): Bitmap {
    val inputBitmap = Bitmap.createScaledBitmap(
        this,
        this.width,
        this.height,
        false
    )
    val outputBitmap = Bitmap.createBitmap(inputBitmap)

    val rs = RenderScript.create(context)

    for (i in 0..iterationsCount) {
        val input = Allocation.createFromBitmap(
            rs,
            outputBitmap
        ) //use this constructor for best performance, because it uses USAGE_SHARED mode which reuses memory
        val output = Allocation.createTyped(rs, input.type)
        val script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
        script.setRadius(25f)
        script.setInput(input)
        script.forEach(output)
        output.copyTo(outputBitmap)
    }
    return outputBitmap
}

fun Bitmap.getDominantColor(): Int {
    return getPaletteOf(0, 0, this.width, this.height).getDominantColor(0xFFFFFF)
}

fun Bitmap.getUpperSideDominantColor(): Int {
    return getPaletteOf(0, 0, this.width, this.height / 2).getDominantColor(0xFFFFFF)
}

fun Bitmap.getLowerSideDominantColor(): Int {
    return getPaletteOf(0, 0, this.width / 2, this.height).getDominantColor(0xFFFFFF)
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

/**
 * Set random background from predefined images for [ImageView] source
 */
fun ImageView.setRandomBackground() {
    setImageDrawable(ContextCompat.getDrawable(OpenseaApplication.context, getRandomDrawable()))
}


/**
 * Get absolute X position of [View] on screen
 */
fun View.absX(): Int {
    val location = IntArray(2)
    this.getLocationOnScreen(location)
    return location[0]
}


fun View.centerX(): Int {
    val myViewRect = Rect()
    getGlobalVisibleRect(myViewRect)
    return myViewRect.centerX()
}


/**
 * Get absolute X position of [View] on screen
 */
fun View.absY(): Int {
    val location = IntArray(2)
    this.getLocationOnScreen(location)
    return location[1]
}

/**
 * Callback on [View] successfully initialized so that it can be measurable
 */
fun View.onInitialized(onInit: () -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (isShown) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                onInit()
            }
        }
    })
}
