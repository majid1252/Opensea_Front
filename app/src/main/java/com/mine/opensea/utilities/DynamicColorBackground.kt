package com.mine.opensea.utilities

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.palette.graphics.Palette
import com.mine.opensea.R
import com.mine.opensea.blur
import com.mine.opensea.getPaletteOf
import kotlinx.coroutines.*

class DynamicColorBackground(context: Context?, attrs: AttributeSet?) :
        RelativeLayout(context, attrs) {

    var diversifyBack = 2
    var iterationCount = 10
    var _alpha = 0.3F
    private var _duration: Long = 200
    var bitmapBackground: Bitmap? = null
    private val palette: Palette by lazy {
        Palette.from(bitmapBackground!!).generate()
    }

    enum class ColorByPos(var color: Int = DEFAULT_COLOR) {
        TopLeft(),
        TopRight(),
        BottomLeft(),
        BottomRight(),
        Total()
    }

    enum class ColorBySwatch(var color: Int = DEFAULT_COLOR) {
        DarkVibrant(),
        LightVibrant(),
        Dominant(),
        Muted(),
        DarkMuted()
    }

    companion object {
        const val DEFAULT_COLOR = R.color.accent_color
    }

    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
    }

    private fun startDrawing() {
        removeAllViews()
        extractColors()
        generateImageBack()
    }

    private fun extractColors() {
        extractColorsByPos()
        extractColorsBySwatch()
        //        setParentBackColor()
    }

    private fun setParentBackColor() {
        (parent as ViewGroup).apply {
            background = ColorBySwatch.Dominant.color.toDrawable().apply { alpha = 15 }
        }
    }

    private fun extractColorsBySwatch() {
        ColorBySwatch.DarkMuted.color = palette.darkMutedSwatch?.rgb ?: DEFAULT_COLOR
        ColorBySwatch.LightVibrant.color = palette.lightVibrantSwatch?.rgb ?: DEFAULT_COLOR
        ColorBySwatch.Dominant.color = palette.dominantSwatch?.rgb ?: DEFAULT_COLOR
        ColorBySwatch.DarkVibrant.color = palette.darkVibrantSwatch?.rgb ?: DEFAULT_COLOR
        ColorBySwatch.Muted.color = palette.mutedSwatch?.rgb ?: DEFAULT_COLOR
    }

    private fun generateImageBack() {
        var bitmap =
            Bitmap.createBitmap(width, if (height == 0) 1 else height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        CoroutineScope(Dispatchers.Main).launch {
            val paint = Paint()

            paint.style = Paint.Style.FILL
            paint.isAntiAlias = true
            paint.isDither = true
            alpha = 0F
            var color: Int
            for (i in 1..diversifyBack + 1) {
                color = when (i) {
                    in 1..diversifyBack + 1 -> ColorBySwatch.LightVibrant.color
                    // TODO() still have not figured it out what color is the best for the background
                    else -> R.color.white
                }
                val centerX = (1..width).random().toFloat()
                val centerY = (1..height).random().toFloat()
                val radius = 600F - i * 50
                paint.color = color
                paint.strokeWidth = 100F - i * 20
                canvas.drawCircle(centerX, centerY, radius, paint)
            }
            bitmap = bitmap.blur(iterationCount)
            background = BitmapDrawable(resources, bitmap)
        }.invokeOnCompletion {
            ObjectAnimator.ofFloat(this, "alpha", 0f, _alpha).apply {
                duration = _duration
                interpolator = AccelerateDecelerateInterpolator()
            }.start()
        }
    }

    private fun createGradient(): GradientDrawable {
        return GradientDrawable(
            GradientDrawable.Orientation.BOTTOM_TOP,
            intArrayOf(
                ColorByPos.Total.color,
                ColorByPos.BottomRight.color,
                ColorByPos.Total.color,
                ColorByPos.BottomLeft.color
            )
        )
    }


    @SuppressLint("ResourceAsColor")
    private fun extractColorsByPos() {
        if (bitmapBackground != null) {
            val width = bitmapBackground!!.width
            val height = bitmapBackground!!.height
            val defColor = ContextCompat.getColor(context, DEFAULT_COLOR)
            ColorByPos.TopLeft.color =
                bitmapBackground!!.getPaletteOf(0, 0, width / 2, height / 2)
                    .getDominantColor(defColor)
            ColorByPos.TopRight.color =
                bitmapBackground!!.getPaletteOf(width / 2, 0, width, height / 2)
                    .getDominantColor(defColor)
            ColorByPos.BottomLeft.color =
                bitmapBackground!!.getPaletteOf(0, height / 2, width / 2, height)
                    .getDominantColor(defColor)
            ColorByPos.BottomRight.color =
                bitmapBackground!!.getPaletteOf(width / 2, height / 2, width, height)
                    .getDominantColor(defColor)
            ColorByPos.Total.color =
                bitmapBackground!!.getPaletteOf(0, 0, width, height)
                    .getDominantColor(defColor)
        }
    }

    public fun draw() {
        if (bitmapBackground != null)
            startDrawing()
    }


}