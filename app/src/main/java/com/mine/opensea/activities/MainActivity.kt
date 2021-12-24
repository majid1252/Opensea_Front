package com.mine.opensea.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.mine.opensea.R
import com.mine.opensea.networking.api.OpenseaRetroService
import com.mine.opensea.viewModels.CollectionListViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val collectionListViewModel: CollectionListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        OpenseaRetroService.create().getCollections(0, 10)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { throwable -> throwable.message?.let { Log.d("error==", it) } }
            .doOnNext { Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show() }
            .subscribe()
    }
}