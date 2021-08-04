package com.example.myposts.models
import com.example.myposts.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myposts.api.ApiClient
import com.example.myposts.api.ApiInterface
import com.example.myposts.ui.PostsRvAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var rvPost:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchPosts()
    }


    fun fetchPosts(){
        rvPost=findViewById(R.id.rvPost)
        var retrofit= ApiClient.buildApiClient(ApiInterface::class.java)
        var request = retrofit.getPosts()
        request.enqueue(object : Callback<List<Posts>> {

            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                if (response.isSuccessful){

                    var postList=response.body()!!
                    //if (postList!=null)

                    var postadapter= PostsRvAdapter(baseContext,postList)
                    rvPost.adapter=postadapter
                    rvPost.layoutManager=LinearLayoutManager(baseContext)
//                    var postDate= mutableListOf<Posts>()
//                        for(x in 1..postList.size)
//                            postDate.add(Posts(2,23,"Developer","to be continued"))
                   // Toast.makeText(baseContext,postlist.size.toString(),Toast.LENGTH_LONG).show()
                }
            }


            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()


            }
        })

    }
}
//nullable variables