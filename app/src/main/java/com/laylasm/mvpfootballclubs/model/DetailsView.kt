package com.laylasm.mvpfootballclubs.model

interface DetailsView {

    interface View{
        fun viewTeamBadgeHome(team: TeamData)
        fun viewTeamBadgeAway(team: TeamData)
        fun setFavoriteState(favList:List<FavoriteDB>)
    }

    interface Presenter{
        fun getTeamsBadgeAway(id:String)
        fun getTeamsBadgeHome(id:String)
        fun deleteMatch(id:String)
        fun checkMatch(id:String)
        fun insertMatch(eventId: String, homeId: String, awayId: String)
        fun onDestroyPresenter()
    }
}