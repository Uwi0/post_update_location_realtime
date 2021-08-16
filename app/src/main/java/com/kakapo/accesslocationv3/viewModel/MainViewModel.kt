package com.kakapo.accesslocationv3.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kakapo.accesslocationv3.model.entities.MapLocation
import com.kakapo.accesslocationv3.model.network.MapApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val mapLocationAPiService = MapApiService()

    val loadMapResponse = MutableLiveData<Boolean>()
    val postMapData = MutableLiveData<MapLocation>()
    val mapResponseLoadingError = MutableLiveData<Boolean>()

    fun postMapLocation(name: String, lat: String, lng: String){
        loadMapResponse.value = true
        compositeDisposable.add(
            mapLocationAPiService
                .postMap(name, lat, lng)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<MapLocation>() {
                    override fun onNext(value: MapLocation?) {
                        loadMapResponse.value = true
                        postMapData.value = value
                        Log.i("Map lat and lng", value.toString())
                        mapResponseLoadingError .value = false
                    }

                    override fun onError(e: Throwable?) {
                        mapResponseLoadingError.value = true
                        loadMapResponse.value = false
                        Log.e("Error Load From API", "cannot get Data, $e")
                    }

                    override fun onComplete() {

                    }

                })
        )
    }

}