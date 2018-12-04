package com.laylasm.mvpfootballclubs.model

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by layla.mardhiyah on 9/12/2018.
 */
interface FootballMatchEndPoint {

    @GET("eventspastleague.php")
    fun getPrevMatch(@Query("id") id: String): Flowable<FootballMatchResponse>

    @GET("eventsnextleague.php")
    fun getNextMatch(@Query("id") id: String): Flowable<FootballMatchResponse>

    @GET("lookupteam.php")
    fun getTeam(@Query("id") id: String): Flowable<TeamResponse>

    @GET("lookupevent.php")
    fun getEventById(@Query("id") id: String): Flowable<FootballMatchResponse>

    @GET("searchteams.php")
    fun getTeamBySearch(@Query("t") query: String) : Flowable<TeamResponse>

    @GET("lookup_all_teams.php")
    fun getAllTeam(@Query("id") id:String) : Flowable<TeamResponse>

    @GET("lookup_all_players.php")
    fun getAllPlayers(@Query("id") id:String?) : Flowable<FootballMatchPlayer>

    @GET("lookupplayer.php")
    fun getPlayerDetail(@Query("id") id:String?) : Flowable<PlayerMatchDetail>

    @GET("searchevents.php")
    fun searchMatches(@Query("e") query: String?) : Flowable<SearchMatchClub>

}


