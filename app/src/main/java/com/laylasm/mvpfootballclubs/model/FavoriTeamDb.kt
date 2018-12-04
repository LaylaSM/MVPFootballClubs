package com.laylasm.mvpfootballclubs.model

class FavoriTeamDb (
        val id: Long?,
        val idTeam: String,
        val urlImage: String
){
    companion object {
        const val TABLE_TEAM: String = "TEAM_TABLE"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_IMAGE: String = "TEAM_IMAGE"
    }
}
