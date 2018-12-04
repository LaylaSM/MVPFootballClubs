package com.laylasm.mvpfootballclubs.model

import android.content.Context
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class LocalRepoImp(private val context: Context) : LocalRepo {

    override fun getTeamFromDb(): List<FavoriTeamDb> {
        lateinit var favoriteList :List<FavoriTeamDb>
        context.database.use {
            val result = select(FavoriTeamDb.TABLE_TEAM)
            val favorite = result.parseList(classParser<FavoriTeamDb>())
            favoriteList = favorite
        }
        return favoriteList
    }

    override fun insertTeamData(teamId: String, imgUrl: String) {
        context.database.use {
            insert(FavoriTeamDb.TABLE_TEAM,
                    FavoriTeamDb.TEAM_ID to teamId,
                    FavoriTeamDb.TEAM_IMAGE to imgUrl)

        }

    }

    override fun deleteTeamData(teamId: String) {
        context.database.use{
            delete(FavoriTeamDb.TABLE_TEAM, "(TEAM_ID = {id})",
                    "id" to teamId)
        }
    }

    override fun checkFavTeam(teamId: String): List<FavoriTeamDb> {
        return context.database.use {
            val result = select(FavoriTeamDb.TABLE_TEAM)
                    .whereArgs("(TEAM_ID = {id})",
                            "id" to teamId)
            val favorite = result.parseList(classParser<FavoriTeamDb>())
            favorite
        }
    }

    override fun checkFavorite(eventId: String): List<FavoriteDB> {
        return context.database.use {
            val result = select(FavoriteDB.TABLE_FAVORITE)
                    .whereArgs("(EVENT_ID = {id})",
                            "id" to eventId)
            val favorite = result.parseList(classParser<FavoriteDB>())
            favorite
        }
    }

    override fun deleteData(eventId: String) {
        context.database.use{
            delete(FavoriteDB.TABLE_FAVORITE, "(EVENT_ID = {id})",
                    "id" to eventId)
        }
    }

    override fun insertData(eventId: String, homeId: String, awayId: String) {
        context.database.use {
            insert(FavoriteDB.TABLE_FAVORITE,
                    FavoriteDB.EVENT_ID to eventId,
                    FavoriteDB.HOME_TEAM_ID to homeId,
                    FavoriteDB.AWAY_TEAM_ID to awayId)

        }
    }

    override fun getMatchFromDb(): List<FavoriteDB> {
        lateinit var favoriteList :List<FavoriteDB>
        context.database.use {
            val result = select(FavoriteDB.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<FavoriteDB>())
            favoriteList = favorite
        }
        return favoriteList
    }
}