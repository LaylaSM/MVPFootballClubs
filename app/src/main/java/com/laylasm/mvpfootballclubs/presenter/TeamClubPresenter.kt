package com.laylasm.mvpfootballclubs.presenter

import com.laylasm.mvpfootballclubs.model.SchedulerProvider
import com.laylasm.mvpfootballclubs.model.TeamClubView
import com.laylasm.mvpfootballclubs.model.TeamMatchRepoImp
import com.laylasm.mvpfootballclubs.model.TeamResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class TeamClubPresenter (val mView : TeamClubView.View, val teamRepositoryImpl: TeamMatchRepoImp,
                         val scheduler: SchedulerProvider): TeamClubView.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun searchTeam(teamName: String) {
        compositeDisposable.add(teamRepositoryImpl.getTeamBySearch(teamName)
                .observeOn(scheduler.ui())
                .subscribeOn(scheduler.io())
                .subscribeWith(object : ResourceSubscriber<TeamResponse>() {
                    override fun onComplete() {
                        mView.hideLoading()
                    }

                    override fun onNext(t: TeamResponse) {
                        mView.displayTeams(t.teams ?: Collections.emptyList())
                    }

                    override fun onError(t: Throwable?) {
                        mView.displayTeams(Collections.emptyList())
                        mView.hideLoading()
                    }

                })
        )
    }

    override fun getTeamData(clubName: String) {
        mView.showLoading()
        compositeDisposable.add(teamRepositoryImpl.getAllTeam(clubName)
                .observeOn(scheduler.ui())
                .subscribeOn(scheduler.io())
                .subscribeWith(object : ResourceSubscriber<TeamResponse>() {
                    override fun onComplete() {
                        mView.hideLoading()
                    }

                    override fun onNext(t: TeamResponse) {
                        mView.displayTeams(t.teams ?: Collections.emptyList())
                    }

                    override fun onError(t: Throwable?) {
                        mView.displayTeams(Collections.emptyList())
                        mView.hideLoading()
                    }

                })
        )
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }

}