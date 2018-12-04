package com.laylasm.mvpfootballclubs.model

interface MatchView {
    interface View{
        fun hideLoading()
        fun showLoading()
        fun displayFootballMatch(matchList:List<Data>)
    }

    interface Presenter{
        fun getFootballMatchData(clubName: String = "4328")
        fun onDestroyPresenter()

    }
}