package com.kakapo.accesslocationv3.model.network

import com.kakapo.accesslocationv3.model.entities.MapLocation
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MapService {

    @FormUrlEncoded
    @POST("map")
    fun postLatLong(
        @Field("name") name: String?,
        @Field("lat") lat: String?,
        @Field("lng") lng: String?,
    ): Observable<MapLocation>
}