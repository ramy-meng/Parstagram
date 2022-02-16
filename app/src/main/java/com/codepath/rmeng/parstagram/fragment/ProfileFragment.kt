package com.codepath.rmeng.parstagram.fragment

import android.util.Log
import com.codepath.rmeng.parstagram.Post
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser

class ProfileFragment: FeedFragment() {

    override fun queryPosts() {

        // Specify which class to query
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        //Find all Post objects
        //This is line is added beacuse use is a pointer
        query.include(Post.KEY_USER)
        //Only return posts from currently signed in user
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser())
        //Return the posts in descending order: ie newer posts will appear first
        query.addDescendingOrder("CreatedAt")

        query.findInBackground(object: FindCallback<Post> {
            override fun done(posts: MutableList<Post>?, e: ParseException?) {
                if (e != null) {
                    //Something went wrong
                    Log.e(TAG, "Error fetching posts")
                } else {
                    if (posts != null) {
                        for (post in posts) {
                            Log.i(TAG, "Post: " + post.getDescription() + ", username: " + post.getUser()?.username)
                        }
                        allPosts.addAll(posts)
                        adapter.notifyDataSetChanged()
                    }
                }
            }

        })
    }
}