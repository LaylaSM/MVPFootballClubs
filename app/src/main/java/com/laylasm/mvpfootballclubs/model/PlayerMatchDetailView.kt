package com.laylasm.mvpfootballclubs.model

interface PlayerMatchDetailView {
    interface View {
        fun viewPlayerDetail(player: PlayerMatch)
    }
    interface Presenter {
        fun getPlayerData(idPlayer: String)
        fun onDestroy()
    }
}