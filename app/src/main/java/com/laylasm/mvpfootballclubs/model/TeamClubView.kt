package com.laylasm.mvpfootballclubs.model

interface TeamClubView {
    interface View{
        fun displayTeams(teamList: List<TeamData>)
        fun hideLoading()
        fun showLoading()

    }
    interface Presenter{
        fun getTeamData(clubName: String)
        fun searchTeam(teamName: String)
        fun onDestroy()
    }
}