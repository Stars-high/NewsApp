package com.capgemini.newsapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsInterface { @GET("top-headlines") //will append to base url
suspend fun getTopHeadlines(@Query("country") code:String,//variable parameters
                            @Query("apiKey")key:String): NewsResult//returns top level object after mapping data in NewsResult data class
    companion object {
        val BASE_URL = "https://newsapi.org/v2/"

        fun getInstance(): NewsInterface {
            val retrofitInstance = Retrofit.Builder()//creating instance
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Gson does parsing
                .build()
            return retrofitInstance.create(NewsInterface::class.java) //created implementation of interface
        }
    }
}