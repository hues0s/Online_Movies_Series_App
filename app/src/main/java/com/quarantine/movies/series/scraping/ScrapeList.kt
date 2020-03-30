package com.quarantine.movies.series.scraping

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.quarantine.movies.series.R
import com.quarantine.movies.series.recycler.RecyclerAdapter
import com.quarantine.movies.series.recycler.RecyclerItemData
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup


class ScrapeList {


    companion object {

        fun getFeaturedList(list: ArrayList<RecyclerItemData>, recyclerAdapter: RecyclerAdapter, context: Context){

            val urlFeatured = "https://www.estrenos10.org/"

            doAsync {

                var contadorDeErrores = 0

                try {

                    val document = Jsoup.connect(urlFeatured).get()
                    val array = document.getElementById("featured-titles")

                    for (element in array.children()) {
                        val imgTag = element.select("img").first()
                        val imageUrl = imgTag.attr("src")
                        val movieTitleFull = imgTag.attr("alt")
                        val movieUrl = element.select("a").first().attr("href")

                        val movieTitleSplit = movieTitleFull.split("(")

                        val movieTitle = movieTitleSplit[0]
                        val year = movieTitleSplit[1].split(")")[0]

                        val recyclerItem = RecyclerItemData(imageUrl, movieTitle, year, movieUrl)
                        list.add(recyclerItem)

                    }

                }
                catch (e: Exception){
                    ++contadorDeErrores
                }

                uiThread {

                    recyclerAdapter.notifyDataSetChanged()

                    if(contadorDeErrores > 0){
                        if(contadorDeErrores == 1) Toast.makeText(context,
                            context.resources.getString(R.string.error_loading_movie), Toast.LENGTH_LONG).show()
                        else
                            Toast.makeText(context, "Error: $contadorDeErrores " +
                                    context.resources.getString(R.string.error_loading_movies), Toast.LENGTH_LONG).show()
                    }

                }


            }

        }


    }



}