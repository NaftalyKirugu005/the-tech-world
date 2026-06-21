package com.example.studentapp

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class Post(val id: Int, val title: String, val body: String)

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>

    companion object {
        private var apiService: ApiService? = null

        fun getInstance(): ApiService {
            if (apiService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                
                apiService = retrofit.create(ApiService::class.java)
                Log.d("ApiService", "Retrofit Instance Created")
            }
            return apiService!!
        }
    }
}
