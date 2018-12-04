package com.laylasm.mvpfootballclubs.model

interface FavMatchView {
    interface View{
        fun hideLoading()
        fun showLoading()
        fun displayFootballMatch(matchList:List<Data>)
        fun hideSwipeRefresh()
    }

    interface Presenter{
        fun getFootballMatchData()
        fun onDestroyPresenter()
    }
}