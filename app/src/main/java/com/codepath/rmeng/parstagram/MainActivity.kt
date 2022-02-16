package com.codepath.rmeng.parstagram

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.FileProvider
import com.parse.*
import java.io.File
import android.os.Environment
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.codepath.rmeng.parstagram.fragment.ComposeFragment
import com.codepath.rmeng.parstagram.fragment.FeedFragment
import com.codepath.rmeng.parstagram.fragment.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView


/**
 * Let user create a post by taking a photo with their camera
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager: FragmentManager = supportFragmentManager

        var fragmentToShow: Fragment? = null

        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener {
            item -> //to rename it the generic name
            when (item.itemId) {
                R.id.action_home -> {
                    //Navigate to the home screen/feed fragment
                    fragmentToShow = FeedFragment()
                    //Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                }
                R.id.action_compose -> {
                    //Navigate to the compose screen
                    fragmentToShow = ComposeFragment()
                    //Toast.makeText(this, "Compose", Toast.LENGTH_SHORT).show()
                }
                R.id.action_profile -> {
                    //Navigate to the profile screen
                    fragmentToShow = ProfileFragment()
                    //Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
                }
            }

            if (fragmentToShow != null) {
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragmentToShow!!).commit()
            }
            //Return true to say that we've handled this user interaction on the item
            true
        }

        // Set default selection
        findViewById<BottomNavigationView>(R.id.bottom_navigation).selectedItemId = R.id.action_home

        //Query for all posts in our server
//        queryPosts()
    }

    companion object {
        const val TAG = "MainActivity"
    }
}