package com.example.myposts.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myposts.R
import com.example.myposts.models.Posts
class PostsRvAdapter(var context:Context,  var postList:List<Posts>):RecyclerView.Adapter<ViewHolderPost>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPost {
       var itemView=LayoutInflater.from(parent.context).inflate(R.layout.postitems,parent,false)
        return ViewHolderPost(itemView)
        //create view holder 2nd
    }

    override fun onBindViewHolder(holder: ViewHolderPost, position: Int) {
        var  currentPost= postList[position]
        holder.tvUserId.text=currentPost.userId.toString()
        holder.tvId.text=currentPost.id.toString()
        holder.tvTitle.text=currentPost.title
        holder.tvBody.text=currentPost.body
        holder.cvPost.setOnClickListener{
            var intent=Intent(context,ViewPostActivity::class.java)
            intent.putExtra("POST_ID",currentPost.id)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
            //when clicked it displays next activity
        }

    }

    override fun getItemCount(): Int {
       return postList.size

    }
}
class ViewHolderPost(itemView:View):RecyclerView.ViewHolder(itemView){
    var tvUserId=itemView.findViewById<TextView>(R.id.tvuserid)
    var tvId=itemView.findViewById<TextView>(R.id.tvBody)
    var tvTitle=itemView.findViewById<TextView>(R.id.tvtitle)
    var tvBody=itemView.findViewById<TextView>(R.id.tvbody)
    var cvPost=itemView.findViewById<CardView>(R.id.cvPost)
}