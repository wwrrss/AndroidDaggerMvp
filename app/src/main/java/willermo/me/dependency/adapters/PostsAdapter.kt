package willermo.me.dependency.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import willermo.me.dependency.R
import willermo.me.dependency.models.Post

class PostsAdapter(var context: Context):RecyclerView.Adapter<PostsAdapter.ViewHolder>(){


    var postList:MutableList<Post> = mutableListOf()

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        var post = postList[position]
        holder?.textViewTitle?.text = post.title
        holder?.textViewBody?.text = post.body
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.post_recycler_row,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  postList.size
    }

    class ViewHolder(var view:View):RecyclerView.ViewHolder(view){
        var textViewTitle:TextView
        var textViewBody:TextView
        init {
            textViewTitle = view.findViewById(R.id.postRowTitle)
            textViewBody = view.findViewById(R.id.postRowBody)
        }

    }
}