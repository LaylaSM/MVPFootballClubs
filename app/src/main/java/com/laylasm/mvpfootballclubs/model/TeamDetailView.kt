package com.laylasm.mvpfootballclubs.model

interface TeamDetailView {

    interface View{
        fun setFavoriteState(favList:List<FavoriTeamDb>)
    }

    interface Presenter{
        fun checkTeam(id:String)
        fun deleteTeam(id:String)
        fun insertTeam(id: String, imgUrl: String)
    }
}