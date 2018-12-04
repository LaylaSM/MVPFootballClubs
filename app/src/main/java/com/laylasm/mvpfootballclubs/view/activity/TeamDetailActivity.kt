package com.laylasm.mvpfootballclubs.view.activity

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.laylasm.mvpfootballclubs.R
import com.laylasm.mvpfootballclubs.model.FavoriTeamDb
import com.laylasm.mvpfootballclubs.model.LocalRepoImp
import com.laylasm.mvpfootballclubs.model.TeamData
import com.laylasm.mvpfootballclubs.model.TeamDetailView
import com.laylasm.mvpfootballclubs.presenter.TeamDetailPresenter
import com.laylasm.mvpfootballclubs.view.adapter.ViewPagerAdapter
import com.laylasm.mvpfootballclubs.view.fragment.PlayerMatchFragment
import com.laylasm.mvpfootballclubs.view.fragment.TeamDescFragment
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.toast


class TeamDetailActivity : AppCompatActivity(), TeamDetailView.View {

    private var isFav: Boolean = false
    private var menuItem: Menu? = null
    lateinit var teamData: TeamData
    lateinit var mPresenter: TeamDetailView.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
        setSupportActionBar(toolbar)

        teamData = if(savedInstanceState != null){
            savedInstanceState.getParcelable("teamData")
        }else{
            intent.getParcelableExtra("teamData")
        }
        val bundle = Bundle()
        bundle.putParcelable("teams", teamData)
        supportActionBar?.title = teamData.strTeam
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadImage()

        val localRepo = LocalRepoImp(applicationContext)
        mPresenter = TeamDetailPresenter(this, localRepo)
        mPresenter.checkTeam(teamData.idTeam)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        val teamFragment = TeamDescFragment()
        val playersFragment = PlayerMatchFragment()
        teamFragment.arguments = bundle
        playersFragment.arguments = bundle
        adapter.fillFragment(teamFragment, "Descriptions")
        adapter.fillFragment(playersFragment, "Players")
        viewpagerTeam.adapter = adapter
        TabLayout.setupWithViewPager(viewpagerTeam)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable("teamData", teamData)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.team_menu, menu)
        menuItem = menu
        setFav()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.team_menu_fav -> {
                if (!isFav){
                    mPresenter.insertTeam(teamData.idTeam, teamData.strTeamBadge)
                    toast("Yeah club berhasil di save ke Favorite coy!")
                    isFav = !isFav
                }else{
                    mPresenter.deleteTeam(teamData.idTeam)
                    toast("Oke oke berhasil dihapus!")
                    isFav = !isFav
                }
                setFav()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun setFavoriteState(favList: List<FavoriTeamDb>) {
        if(!favList.isEmpty()) isFav = true
    }

    private fun loadImage(){
        if (!teamData.strTeamFanart2.equals(null)){
        Glide.with(applicationContext)
                .load(teamData.strTeamFanart2)
                .apply(RequestOptions().placeholder(R.drawable.ic_add_fav_24dp))
                .apply(RequestOptions().override(220, 160))
                .into(ImageViewTeamDetails)
        }else{
            Glide.with(applicationContext)
                    .load(teamData.strTeamBadge)
                    .apply(RequestOptions().placeholder(R.drawable.ic_add_fav_24dp))
                    .apply(RequestOptions().override(120, 140))
                    .into(ImageViewTeamDetails)
        }
    }

    private fun setFav() {
        if (isFav)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_fav_24dp)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_fav_24dp)
    }


}
