package com.laylasm.mvpfootballclubs.presenter

import com.laylasm.mvpfootballclubs.model.LocalRepoImp
import com.laylasm.mvpfootballclubs.model.TeamDetailView

class TeamDetailPresenter(val mView: TeamDetailView.View,
                          val localRepositoryImpl: LocalRepoImp):
        TeamDetailView.Presenter {

    override fun checkTeam(id: String) {
        mView.setFavoriteState(localRepositoryImpl.checkFavTeam(id))
    }

    override fun deleteTeam(id: String) {
        localRepositoryImpl.deleteTeamData(id)
    }

    override fun insertTeam(id: String, imgUrl: String) {
        localRepositoryImpl.insertTeamData(id, imgUrl)
    }


}