package com.laylasm.mvpfootballclubs.model

import io.reactivex.Flowable

interface TeamMatchRepo {

    fun getTeams(id: String = "0"): Flowable<TeamResponse>

    fun getTeamsDetail(id: String = "0"): Flowable<TeamResponse>

    fun getTeamBySearch(query: String): Flowable<TeamResponse>

    fun getAllTeam(id: String): Flowable<TeamResponse>
}