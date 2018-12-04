package com.laylasm.mvpfootballclubs.model

interface SearchMatchView {

    interface View{
        fun showLoading()
        fun hideLoading()
        fun viewMatch(matchList: List<Data>)
    }
    interface Presenter{
        fun searchMatch(query: String?)
        fun onDestroy()
    }

}