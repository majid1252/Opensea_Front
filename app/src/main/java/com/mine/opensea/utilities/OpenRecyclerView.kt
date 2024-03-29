package com.mine.opensea.utilities

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.mine.opensea.OpenseaApplication

class OpenRecyclerView(context: Context, attributeSet: AttributeSet) :
        RecyclerView(context, attributeSet) {


    init {
        onFlingListener = object : OnFlingListener() {
            override fun onFling(velocityX: Int, velocityY: Int): Boolean {
                Log.d("Fling:", "vX= $velocityX vY= $velocityY")
                onGlobalFling.onFling(velocityX, velocityY)
                return false
            }
        }
    }

    companion object {
        private lateinit var onGlobalFling: OnFlingListener
        public fun addGlobalFlingListener(onFlingListener: OnFlingListener) {
            onGlobalFling = onFlingListener
        }

    }

}