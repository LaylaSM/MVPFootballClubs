package com.laylasm.mvpfootballclubs.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Favorite.db", null, 1) {

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(p0: SQLiteDatabase) {
        p0.createTable(FavoriteDB.TABLE_FAVORITE, true,
                FavoriteDB.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteDB.EVENT_ID to TEXT + UNIQUE,
                FavoriteDB.HOME_TEAM_ID to TEXT,
                FavoriteDB.AWAY_TEAM_ID to TEXT)
        p0.createTable(FavoriTeamDb.TABLE_TEAM, true,
                FavoriTeamDb.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriTeamDb.TEAM_ID to TEXT,
                FavoriTeamDb.TEAM_IMAGE to TEXT)
    }

    override fun onUpgrade(p0: SQLiteDatabase, p1: Int, p2: Int) {
        p0.dropTable(FavoriteDB.TABLE_FAVORITE, true)
        p0.dropTable(FavoriTeamDb.TABLE_TEAM, true)
    }
}
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)