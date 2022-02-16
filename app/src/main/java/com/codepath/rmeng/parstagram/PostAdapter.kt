package com.codepath.rmeng.parstagram

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationItemView

class PostAdapter(val context: Context, val posts: MutableList<Post>)
    : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.ViewHolder {
        //Specify the layout file to use for this item

        val view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostAdapter.ViewHolder, position: Int) {
        //Get the specific post that we want to inflate
        val post = posts.get(position)
        //Display the relevant information
        holder.bind(post)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvUsername: TextView
        val ivImage: ImageView
        val tvDescription: TextView

        init {
            tvUsername = itemView.findViewById(R.id.tvUsername)
            ivImage = itemView.findViewById(R.id.ivImage)
            tvDescription = itemView.findViewById((R.id.tvDescription))
        }

        fun bind(post: Post) {
            tvDescription.text = post.getDescription()
            tvUsername.text = post.getUser()?.username

            //Populate image glide library get the imageurl and load into ivImage
            Glide.with(itemView.context).load(post.getImage()?.url).into(ivImage)
        }
    }

    /* Within the RecyclerView.Adapter class */

    // Clean all elements of the recycler
    fun clear() {
        posts.clear()
        notifyDataSetChanged()
    }

    // Add a list of items -- change to type used
    fun addAll(postList: List<Post>) {
        posts.addAll(postList)
        notifyDataSetChanged()
    }
}