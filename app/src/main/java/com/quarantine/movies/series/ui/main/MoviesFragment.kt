package com.quarantine.movies.series.ui.main


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.quarantine.movies.series.R
import com.quarantine.movies.series.recycler.RecyclerAdapter
import com.quarantine.movies.series.recycler.RecyclerItemData
import android.content.Context.INPUT_METHOD_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.quarantine.movies.series.scraping.ScrapeList


class MoviesFragment : Fragment() {

    private val recyclerListFeatured = ArrayList<RecyclerItemData>()
    private val recyclerListNewArrivals = ArrayList<RecyclerItemData>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_movies, container, false)

        setUpRecyclerView(view)

        setUpSearchBar(view)

        return view
    }

    private fun setUpRecyclerView(v: View) {

        //Featured movies recycler view

        val recyclerView = v.findViewById<RecyclerView>(R.id.featured_movies_recycler_view)
        recyclerView.setHasFixedSize(true)

        val linearLayoutManager = LinearLayoutManager(v.context)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.setItemViewCacheSize(10)

        val adapter = RecyclerAdapter(recyclerListFeatured, v.context, activity!!)
        recyclerView.adapter = adapter

        ScrapeList.getFeaturedList(recyclerListFeatured, adapter, v.context)


        //New arrivals movies recycler view

        val recyclerViewNewArrivals = v.findViewById<RecyclerView>(R.id.new_arrivals_movies_recycler_view)
        recyclerViewNewArrivals.setHasFixedSize(true)

        val linearLayoutManagerNew = LinearLayoutManager(v.context)
        linearLayoutManagerNew.orientation = LinearLayoutManager.HORIZONTAL
        recyclerViewNewArrivals.layoutManager = linearLayoutManagerNew
        recyclerViewNewArrivals.setItemViewCacheSize(10)

        val adapterNewArrivals = RecyclerAdapter(recyclerListNewArrivals, v.context, activity!!)
        recyclerViewNewArrivals.adapter = adapterNewArrivals

        //scrape list etc...

    }

    private fun setUpSearchBar(v: View) {

        val editText = v.findViewById<EditText>(R.id.movies_search_text)

        editText.onFocusChangeListener = object: View.OnFocusChangeListener{
            override fun onFocusChange(p0: View?, p1: Boolean) {
                if(p1) editText.isCursorVisible = p1
                else hideFocusedElements(activity!!, v)
            }
        }

        val searchButton = v.findViewById<Button>(R.id.movies_search_button)
        searchButton.setOnClickListener {
            hideFocusedElements(activity!!, v)
            Toast.makeText(v.context, "searching...", Toast.LENGTH_SHORT).show()
        }





    }

    private fun hideFocusedElements(activity: Activity, v: View) {
        val imm = activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(v.applicationWindowToken, 0)
        val editText = v.findViewById<EditText>(R.id.movies_search_text)
        editText.isCursorVisible = false
    }




}
