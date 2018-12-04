package com.laylasm.mvpfootballclubs.view.fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.SearchView
import android.view.*
import com.laylasm.mvpfootballclubs.R
import com.laylasm.mvpfootballclubs.view.adapter.ViewPagerAdapter
import com.laylasm.mvpfootballclubs.view.activity.SearchMatchActivity
import org.jetbrains.anko.startActivity

class ScheduleClubFragment  : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_schedule_club, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_search, menu)

        val searchView = menu?.findItem(R.id.item_menu_search)?.actionView as SearchView?

        searchView?.queryHint = "Cari Club apa?"

        searchView?.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                context?.startActivity<SearchMatchActivity>("query" to query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }
        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = view.findViewById<ViewPager>(R.id.viewPagerMatch)
        val tabs = view.findViewById<TabLayout>(R.id.TabLayout)
        val adapter = ViewPagerAdapter(childFragmentManager)
        setHasOptionsMenu(true)
        adapter.fillFragment(PrevMatchsFragment(), "Prev Match")
        adapter.fillFragment(NextMatchsFragment(), "Next Match")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }


}
