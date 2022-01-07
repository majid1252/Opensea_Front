package com.mine.opensea.utilities

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.core.view.drawToBitmap
import com.google.android.renderscript.Toolkit
import com.mine.opensea.R
import com.mine.opensea.blur
import com.mine.opensea.fragments.CollectionDetailsFragment
import com.mine.opensea.getPaletteOf
import kotlinx.coroutines.*
import java.util.logging.Handler

class DynamicColorBackground(context: Context?, attrs: AttributeSet?) :
        RelativeLayout(context, attrs) {
    public var diversifyBack = 4
    public var bitmapBackground: Bitmap? = null

    enum class ColorByPos(var color: Int = 0x0000FF) {
        TopLeft(),
        TopRight(),
        BottomLeft(),
        BottomRight(),
        Total()
    }

    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
    }

    private fun addImageBackgrounds() {
        removeAllViews()
        extractColors()
        for (i in 0..diversifyBack) {
            addView(generateImageBack(i))
        }
    }

    @DelicateCoroutinesApi
    private fun generateImageBack(seed: Int): ImageView {

        var bitmap = Bitmap.createBitmap(700, 1000, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val imageV = ImageView(context)

        val paint = Paint()
        paint.color = ColorByPos.Total.color
        paint.strokeWidth = 300F
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true
        paint.isDither = true

        val centerX = (width / 2).toFloat()
        val centerY = (height / 2).toFloat()
        val radius = 300F

        // TODO() calculating blur should be off loaded
        GlobalScope.launch(Dispatchers.IO) {
            bitmap = bitmap.blur(10)
        }

        canvas.drawCircle(centerX, centerY, radius, paint)

        alpha = 0.5f
        return imageV
    }

    suspend fun calculateBlur(bitmap: Bitmap) {
        coroutineScope {
            val remoteImageDeferred = async(Dispatchers.IO) {

            }
            val imageBitmap = remoteImageDeferred.await()
            launch(Dispatchers.Default) {
                val filterBitmap = bitmap.blur(10)
                withContext(Dispatchers.Main) {
                    background = BitmapDrawable(resources, filterBitmap)
                }
            }
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


    private fun extractColors() {
        if (bitmapBackground != null) {
            val width = bitmapBackground!!.width
            val height = bitmapBackground!!.height
            ColorByPos.TopLeft.color =
                bitmapBackground!!.getPaletteOf(0, 0, width / 2, height / 2)
                    .getDominantColor(0x0000FF)
            ColorByPos.TopRight.color =
                bitmapBackground!!.getPaletteOf(width / 2, 0, width, height / 2)
                    .getDominantColor(0x0000FF)
            ColorByPos.BottomLeft.color =
                bitmapBackground!!.getPaletteOf(0, height / 2, width / 2, height)
                    .getDominantColor(0x0000FF)
            ColorByPos.BottomRight.color =
                bitmapBackground!!.getPaletteOf(width / 2, height / 2, width, height)
                    .getDominantColor(0x0000FF)
            ColorByPos.Total.color =
                bitmapBackground!!.getPaletteOf(0, 0, width, height)
                    .getDominantColor(0x0000FF)
            background = ColorDrawable(ColorByPos.Total.color)
        }
    }

    public fun draw() {
        addImageBackgrounds()
    }

}