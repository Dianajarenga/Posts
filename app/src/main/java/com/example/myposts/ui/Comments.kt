package com.example.myposts.ui
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import com.example.myposts.R
import com.example.myposts.api.ApiClient
import com.example.myposts.api.ApiInterface
import com.example.myposts.models.Comment
import com.example.myposts.models.Posts

import retrofit2.Response

class Comments : AppCompatActivity() {
    lateinit var tvPostTitle: TextView
    lateinit var tvPostBody: TextView
    lateinit var rvComments:RecyclerView

    var post_id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)
        post_id = intent.getIntExtra("post_id", 0)
        castComments()
        getPost()
        getComments()


    }

    fun castComments() {
        tvPostTitle = findViewById(R.id.tvPostTitle)
        tvPostBody = findViewById(R.id.tvPostBody)
        rvComments=findViewById(R.id.rvComments)

    }

    fun getPost() {
        if (post_id == 0) {
            finish()
        }

        var client = ApiClient.buildApiClient(ApiInterface::class.java)
        var request = client.getPost(post_id)
        request.enqueue(object : Callback<Posts> {
            override fun onResponse(call: Call<Posts>, response: Response<Posts>) {
                if (response.isSuccessful) {
                    var post = response.body()
                    tvPostBody.text = post?.body
                    tvPostTitle.text = post?.title

                }

            }

            override fun onFailure(call: Call<Posts>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun getComments() {
        var client = ApiClient.buildApiClient(ApiInterface::class.java)
        var request = client.getComments(post_id)
        request.enqueue(object : Callback<List<Comment>>{
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
              if (response.isSuccessful){
                  var newComment=response.body()!!
                  var commentsAdapter=CommentsAdapter(baseContext,newComment)
                  rvComments.adapter=commentsAdapter
                  rvComments.layoutManager=LinearLayoutManager(baseContext)

              }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
               Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }
        }

        )
    }
}
