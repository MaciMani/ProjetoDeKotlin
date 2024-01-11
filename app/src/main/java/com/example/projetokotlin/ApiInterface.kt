package com.example.projetokotlin
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface ApiInterface {

    @Headers("X-RapidAPI-Key: ac0aeda88fmsha5e8298c08109d7p194a9cjsndc0df90d70bd", "X-RapidAPI-Host: deezerdevs-deezer.p.rapidapi.com")
    @GET("search")
    fun getData(@Query("q") query: String) : Call<MusicData?>


}