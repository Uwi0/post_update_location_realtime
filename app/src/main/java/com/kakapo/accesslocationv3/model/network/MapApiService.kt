package com.kakapo.accesslocationv3.model.network

import com.kakapo.accesslocationv3.model.entities.MapLocation
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MapApiService {

    private val api = Retrofit.Builder().baseUrl(MapAPi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(MapService::class.java)

    fun postMap(name: String, latitude: String, longitude: String): Observable<MapLocation> {
        return api.postLatLong(name, latitude, longitude)
    }
}