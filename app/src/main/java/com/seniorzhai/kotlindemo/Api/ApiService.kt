package com.seniorzhai.kotlindemo.Api

import com.seniorzhai.kotlindemo.Model.History
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import rx.Observable

/**
 * Created by zhai on 16/3/4.
 */
interface ApiService {

    @GET("day/history")
    fun histroy(): Observable<History>

    companion object {
        fun create(): ApiService {
            val restAdapter = Retrofit.Builder()
                    .baseUrl("http://gank.io/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build()

            return restAdapter.create(ApiService::class.java)
        }
    }
}