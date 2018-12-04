package com.laylasm.mvpfootballclubs.view.fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.laylasm.mvpfootballclubs.R
import com.laylasm.mvpfootballclubs.view.adapter.ViewPagerAdapter

class FavClubFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_fav, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = view.findViewById<ViewPager>(R.id.viewPagerMatch)
        val tabs = view.findViewById<TabLayout>(R.id.TabLayout)
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.fillFragment(FavMatchFragment(), "MATCH")
        adapter.fillFragment(FavTeamFragment(), "TEAMS")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }


}
