package com.laylasm.mvpfootballclubs.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.laylasm.mvpfootballclubs.R
import com.laylasm.mvpfootballclubs.model.MainView
import com.laylasm.mvpfootballclubs.view.fragment.FavClubFragment
import com.laylasm.mvpfootballclubs.view.fragment.ScheduleClubFragment
import com.laylasm.mvpfootballclubs.view.fragment.TeamsClubFragment
import kotlinx.android.synthetic.main.bottom_nav_view.*

class MainActivity : AppCompatActivity(), MainView.View{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.prevMatch -> {
                    loadMatch(savedInstanceState)
                }
                R.id.nextMatch -> {
                    loadNextMatch(savedInstanceState)
                }
                R.id.favMatch -> {
                    loadFavoriteMatch(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = R.id.prevMatch
    }

    private fun loadMatch(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.flMainContainer, ScheduleClubFragment(), ScheduleClubFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadFavoriteMatch(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.flMainContainer, FavClubFragment(), FavClubFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadNextMatch(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.flMainContainer, TeamsClubFragment(), TeamsClubFragment::class.java.simpleName)
                    .commit()
        }
    }
}
