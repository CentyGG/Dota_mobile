package com.example.dotamobile.data

import com.example.dotamobile.dataClasses.Hero
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.Objects

interface OpenDotaApi {
    @GET("heroes")
    suspend fun getHeroes(): Array<Hero>
    //eewqeweq
}