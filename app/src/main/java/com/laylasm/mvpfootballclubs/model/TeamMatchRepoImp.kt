package com.laylasm.mvpfootballclubs.model

import io.reactivex.Flowable

class TeamMatchRepoImp(val footballMatchEndPoint: FootballMatchEndPoint) : TeamMatchRepo{

    override fun getAllTeam(id: String) = footballMatchEndPoint.getAllTeam(id)

    override fun getTeams(id: String): Flowable<TeamResponse> = footballMatchEndPoint.getAllTeam(id)

    override fun getTeamBySearch(query: String) = footballMatchEndPoint.getTeamBySearch(query)

    override fun getTeamsDetail(id: String): Flowable<TeamResponse> = footballMatchEndPoint.getTeam(id)

}