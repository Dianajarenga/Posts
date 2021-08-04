package com.example.myposts.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.myposts.R
import com.example.myposts.api.ApiClient
import com.example.myposts.api.ApiInterface
import com.example.myposts.models.Posts
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ViewPostActivity : AppCompatActivity() {
    var postId=0
    lateinit var tvPostTitle:TextView
    lateinit var tvPostBody:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_post)
        postId=intent.getIntExtra("POST_ID",0)
        tvPostTitle=findViewById(R.id.tvPostTitle)
        tvPostBody=findViewById(R.id.tvPostBody)
        fetchPostById()
    }
    fun fetchPostById(){
        var apiClient=ApiClient.buildApiClient(ApiInterface::class.java)
        var request=apiClient.getPost(postId)
        request.enqueue(object : Callback<Posts> {

            override fun onResponse(call: Call<Posts>, response: Response<Posts>) {
               if (response.isSuccessful){
                   var post=response.body()
                    tvPostTitle.text=post?.title
                    tvPostBody.text=post?.body
               }
            }

            override fun onFailure(call: Call<Posts>, t: Throwable) {
               Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
        }

            })
    }
}
//retrofit callback functions enque