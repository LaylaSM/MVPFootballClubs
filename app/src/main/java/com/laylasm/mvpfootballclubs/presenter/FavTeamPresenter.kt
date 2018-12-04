package com.laylasm.mvpfootballclubs.presenter

import com.laylasm.mvpfootballclubs.model.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class FavTeamPresenter(val mView: FavTeamView.View,
                            val localRepositoryImpl: LocalRepoImp,
                            val teamRepositoryImpl: TeamMatchRepoImp,
                            val scheduler: SchedulerProvider): FavTeamView.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getTeamData() {
        mView.showLoading()
        val teamList = localRepositoryImpl.getTeamFromDb()
        var teamLists: MutableList<TeamData> = mutableListOf()
        for (fav in teamList){
            compositeDisposable.add(teamRepositoryImpl.getTeamsDetail(fav.idTeam)
                    .observeOn(scheduler.ui())
                    .subscribeOn(scheduler.io())
                    .subscribeWith(object: ResourceSubscriber<TeamResponse>(){
                        override fun onComplete() {
                            mView.hideLoading()
                            mView.hideSwipeRefresh()
                        }

                        override fun onNext(t: TeamResponse) {
                            teamLists.add(t.teams[0])
                            mView.displayTeams(teamLists)
                        }

                        override fun onError(t: Throwable?) {
                            mView.hideLoading()
                            mView.hideSwipeRefresh()
                            mView.displayTeams(Collections.emptyList())
                        }

                    })
            )
        }

        if(teamList.isEmpty()){
            mView.hideLoading()
            mView.hideSwipeRefresh()
            mView.displayTeams(teamLists)
        }
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}