package com.laylasm.mvpfootballclubs.model

import io.reactivex.Flowable

interface MatchRepo {

    fun getFootballMatch(id : String) : Flowable<FootballMatchResponse>

    fun getNextMatch(id : String) : Flowable<FootballMatchResponse>

    fun getEventById(id: String) : Flowable<FootballMatchResponse>

    fun searchMatches(query: String?) : Flowable<SearchMatchClub>
}