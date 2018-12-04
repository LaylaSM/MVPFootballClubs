package com.laylasm.mvpfootballclubs.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.laylasm.mvpfootballclubs.R
import com.laylasm.mvpfootballclubs.model.*
import com.laylasm.mvpfootballclubs.presenter.PlayerMatchDetailPresenter
import kotlinx.android.synthetic.main.activity_player_detail.*
import kotlinx.android.synthetic.main.player_details_page.*

class PlayerMatchDetailActivity : AppCompatActivity(), PlayerMatchDetailView.View {

    lateinit var player: PlayerMatch
    lateinit var mPresenter: PlayerMatchDetailView.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)
        setSupportActionBar(toolbar)
        player = intent.getParcelableExtra("player")
        supportActionBar?.title = player.strPlayer
        val service = FootballMatchApi.getClient().create(FootballMatchEndPoint::class.java)
        val request = PlayerMatchRepoImp(service)
        val scheduler = AppSchedulerProvider()
        mPresenter = PlayerMatchDetailPresenter(this, request, scheduler)
        mPresenter.getPlayerData(player.idPlayer)
    }

    override fun viewPlayerDetail(player: PlayerMatch) {
        initView(player)
    }

    private fun initView(player: PlayerMatch){
        loadImageCollaps(player)
        Glide.with(applicationContext)
                .load(player.strCutout)
                .apply(RequestOptions().placeholder(R.drawable.ic_add_fav_24dp))
                .into(ivPlayerDetails)


        tvPlayerDetailsName.text = player.strPlayer
        tvPlayerDetailsNamePosition.text = player.strPosition
        tvPlayerDetailsBirthDate.text = player.dateBorn
        tvPlayerDesc.text = player.strDescriptionEN
    }

    private fun loadImageCollaps(player: PlayerMatch){
        if(!player.strFanart2.equals(null)){
            Glide.with(applicationContext)
                    .load(player.strFanart2)
                    .apply(RequestOptions().placeholder(R.drawable.ic_add_fav_24dp))
                    .into(imageBannerPlayerDetail)
        }else{
            Glide.with(applicationContext)
                    .load(player.strThumb)
                    .apply(RequestOptions().placeholder(R.drawable.ic_add_fav_24dp))
                    .into(imageBannerPlayerDetail)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroy()
    }
}
