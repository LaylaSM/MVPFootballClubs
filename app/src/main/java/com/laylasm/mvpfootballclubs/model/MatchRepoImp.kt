package com.laylasm.mvpfootballclubs.model

import io.reactivex.Flowable

class MatchRepoImp(private val footballMatchEndPoint: FootballMatchEndPoint) : MatchRepo {

    override fun getEventById(id: String): Flowable<FootballMatchResponse> = footballMatchEndPoint.getEventById(id)

    override fun getNextMatch(id: String): Flowable<FootballMatchResponse> = footballMatchEndPoint.getNextMatch(id)

    override fun getFootballMatch(id: String): Flowable<FootballMatchResponse> = footballMatchEndPoint.getPrevMatch(id)

    override fun searchMatches(query: String?) = footballMatchEndPoint.searchMatches(query)

}