package com.laylasm.mvpfootballclubs.model

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by layla.mardhiyah on 9/12/2018.
 */
class FootballMatchApi {
    companion object {
        fun getClient() : Retrofit {
            return Retrofit.Builder ()
                    .baseUrl("https://thesportsdb.com/api/v1/json/1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    . build()
        }
    }
}
