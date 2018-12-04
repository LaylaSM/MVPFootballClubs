package com.laylasm.mvpfootballclubs.model

class PlayerMatchRepoImp (private val footballMatchEndPoint: FootballMatchEndPoint): PlayerMatchRepo {

    override fun getAllPlayers(teamId: String?) = footballMatchEndPoint.getAllPlayers(teamId)

    override fun getPlayerDetail(playerId: String?) = footballMatchEndPoint.getPlayerDetail(playerId)
}