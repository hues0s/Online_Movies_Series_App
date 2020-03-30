package com.quarantine.movies.series.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.quarantine.movies.series.R

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    private var initialPos: Int = -1
    private var fragment = Fragment()

    override fun getItem(position: Int): Fragment {
        if(initialPos != position) {

            when (position) {
                0 -> {
                    fragment = MoviesFragment()
                }
                1 -> {
                    fragment = SeriesFragment()
                }
            }

            initialPos = position
        }
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}