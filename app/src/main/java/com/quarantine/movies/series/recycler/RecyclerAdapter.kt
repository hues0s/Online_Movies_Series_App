package com.quarantine.movies.series.recycler

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.quarantine.movies.series.MainMenuActivity
import com.quarantine.movies.series.MovieActivity
import com.quarantine.movies.series.R


class RecyclerAdapter(l: ArrayList<RecyclerItemData>, c: Context, a: Activity) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private val list = l
    private val context = c

    private val mainMenuActivity = a


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.progressBar.visibility = View.VISIBLE

        Glide
            .with(context)
            .load(list[position].imageUrl)
            .centerCrop()
            .listener(object: RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.progressBar.visibility = View.GONE
                    return false
                }

            })
            .into(holder.cartel)

        holder.titulo.text = list[position].title
        holder.año.text = list[position].year

        holder.cartel.setOnClickListener {

            val i = Intent(context, MovieActivity::class.java)
            i.putExtra("imageUrl", list[position].imageUrl)
            i.putExtra("movieUrl", list[position].movieUrl)
            i.putExtra("title", list[position].title)
            i.putExtra("year", list[position].year)


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val activityOptions = ActivityOptions.makeSceneTransitionAnimation(mainMenuActivity,
                    holder.cartel, "sharedTransitionName")
                context.startActivity(i, activityOptions.toBundle())
            }

            else{
                context.startActivity(i)
            }


        }

    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        val progressBar = v.findViewById<ProgressBar>(R.id.item_progress_bar)

        val cartel = v.findViewById<ImageView>(R.id.item_image)
        val titulo = v.findViewById<TextView>(R.id.item_title)
        var año = v.findViewById<TextView>(R.id.item_year)
    }

}