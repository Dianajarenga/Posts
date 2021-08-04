
package com.example.myposts.api

import com.example.myposts.models.Comment
import com.example.myposts.models.Posts

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET ("posts")
    fun getPosts(): Call<List<Posts>>

    @GET ("posts/{postId}")
    fun getPost(@Path("postId")id:Int):Call<Posts>

    @GET ("posts/{postId}/comments")
   fun getComments(@Path("postId")id: Int):Call<List<Comment>>

}





