package com.example.api1.data.service

import com.example.api1.data.model.Post
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): List<Post>

}