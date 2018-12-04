package com.laylasm.mvpfootballclubs.model

interface PlayerMatchView {
    interface View{
        fun showLoading()
        fun hideLoading()
        fun displayPlayers(playerList: List<PlayerMatch>)

    }
    interface Presenter{
        fun getAllPlayer(teamId: String?)
        fun onDestroy()
    }

}