package com.laylasm.mvpfootballclubs.presenter

import com.laylasm.mvpfootballclubs.model.DetailsView
import com.laylasm.mvpfootballclubs.model.LocalRepoImp
import com.laylasm.mvpfootballclubs.model.TeamMatchRepoImp
import com.laylasm.mvpfootballclubs.model.TeamResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber

class DetailsPresenter(val mView : DetailsView.View, val teamRepositoryImpl: TeamMatchRepoImp,
                      val localRepositoryImpl: LocalRepoImp) : DetailsView.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun checkMatch(id: String) {
        mView.setFavoriteState(localRepositoryImpl.checkFavorite(id))
    }

    override fun deleteMatch(id: String) {
        localRepositoryImpl.deleteData(id)
    }

    override fun insertMatch(eventId: String, homeId: String, awayId: String) {
        localRepositoryImpl.insertData(eventId, homeId, awayId)
    }

    override fun getTeamsBadgeHome(id: String) {
        compositeDisposable.add(teamRepositoryImpl.getTeamsDetail(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object: ResourceSubscriber<TeamResponse>(){
                    override fun onComplete() {

                    }

                    override fun onNext(t: TeamResponse) {
                        mView.viewTeamBadgeHome(t.teams[0])
                    }

                    override fun onError(t: Throwable?) {

                    }
                })
        )
    }

    override fun getTeamsBadgeAway(id:String) {
        compositeDisposable.add(teamRepositoryImpl.getTeamsDetail(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object: ResourceSubscriber<TeamResponse>(){
                    override fun onComplete() {

                    }

                    override fun onNext(t: TeamResponse) {
                        mView.viewTeamBadgeHome(t.teams[0])
                    }

                    override fun onError(t: Throwable?) {

                    }
                })
        )
    }

    override fun onDestroyPresenter() {
        compositeDisposable.dispose()
    }
}