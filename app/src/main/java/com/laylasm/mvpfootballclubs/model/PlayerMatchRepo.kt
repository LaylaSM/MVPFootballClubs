package com.laylasm.mvpfootballclubs.model

import io.reactivex.Flowable

interface PlayerMatchRepo {

    fun getAllPlayers(teamId: String?) : Flowable<FootballMatchPlayer>

    fun getPlayerDetail(playerId: String?) : Flowable<PlayerMatchDetail>
}