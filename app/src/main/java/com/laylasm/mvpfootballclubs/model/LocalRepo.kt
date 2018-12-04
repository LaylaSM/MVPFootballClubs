package com.laylasm.mvpfootballclubs.model

interface LocalRepo {

    fun getMatchFromDb() : List<FavoriteDB>

    fun insertData(eventId: String, homeId: String, awayId: String)

    fun deleteData(eventId: String)

    fun checkFavorite(eventId: String) : List<FavoriteDB>

    fun getTeamFromDb() : List<FavoriTeamDb>

    fun insertTeamData(teamId: String, imgUrl: String)

    fun deleteTeamData(teamId: String)

    fun checkFavTeam(teamId: String) : List<FavoriTeamDb>
}