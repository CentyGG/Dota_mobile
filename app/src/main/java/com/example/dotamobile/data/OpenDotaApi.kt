package com.example.dotamobile.data

import com.example.dotamobile.dataClasses.Hero
import retrofit2.http.GET
import retrofit2.http.Path

interface OpenDotaApi {
    @GET("heroes/{hero_id}")
    suspend fun getHeroes(@Path("id") id: CharSequence): Hero
    //eewqeweq
}