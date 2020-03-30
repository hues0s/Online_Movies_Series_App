package com.quarantine.movies.series

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide

class MovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)


        val imageUrl = intent?.extras?.getString("imageUrl")
        val movieUrl = intent?.extras?.getString("movieUrl")
        val title = intent?.extras?.getString("title")
        val year = intent?.extras?.getString("year")

        val cartelImageView = findViewById<ImageView>(R.id.activity_movie_image)
        Glide
            .with(this)
            .load(imageUrl)
            .centerCrop()
            .into(cartelImageView)


    }
}
