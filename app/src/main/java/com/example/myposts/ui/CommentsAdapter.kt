package com.example.myposts.ui



    import android.content.Context
    import android.content.Intent
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.TextView
    import androidx.recyclerview.widget.RecyclerView
    import com.example.myposts.R
    import com.example.myposts.models.Comment


class CommentsAdapter( var context: Context ,var commentContent: List<Comment>):RecyclerView.Adapter<CommentsViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
            var itemView= LayoutInflater.from(parent.context).inflate(R.layout.activity_comments, parent,false)
            return CommentsViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
            var currentComment=commentContent[position]
            holder.tvComName.text=currentComment.name
            holder.tvComEmail.text=currentComment.email
            holder.tvComBody.text=currentComment.body
            holder.dvComments.setOnClickListener{
                var intent= Intent(context,ViewPostActivity::class.java)
                intent.putExtra("POST_ID",currentComment.id)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
                //when clicked it displays next activity
            }
        }

        override fun getItemCount(): Int {
            return commentContent.size
        }
    }
    class CommentsViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var tvComName=itemView.findViewById<TextView>(R.id.tvComName)
        var tvComEmail=itemView.findViewById<TextView>(R.id.tvComEmail)
        var tvComBody=itemView.findViewById<TextView>(R.id.tvComBody)
        var dvComments=itemView.findViewById<TextView>(R.id.dvComments)

    }

