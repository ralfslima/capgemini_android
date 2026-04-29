package com.example.api1.data.repository

import com.example.api1.data.model.Post
import com.example.api1.data.remote.RetrofitClient

class PostRepository {

    suspend fun getPosts(): List<Post> {
        return RetrofitClient.api.getPosts()
    }

}