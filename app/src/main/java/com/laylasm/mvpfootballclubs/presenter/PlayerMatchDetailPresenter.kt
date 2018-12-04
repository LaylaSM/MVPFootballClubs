package com.laylasm.mvpfootballclubs.presenter

import com.laylasm.mvpfootballclubs.model.PlayerMatchDetail
import com.laylasm.mvpfootballclubs.model.PlayerMatchDetailView
import com.laylasm.mvpfootballclubs.model.PlayerMatchRepoImp
import com.laylasm.mvpfootballclubs.model.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber

class PlayerMatchDetailPresenter (val mView: PlayerMatchDetailView.View,
                                  val playersRepositoryImpl: PlayerMatchRepoImp,
                                  val schedulerProvider: SchedulerProvider): PlayerMatchDetailView.Presenter {


    val compositeDisposable = CompositeDisposable()

    override fun getPlayerData(idPlayer: String) {
        compositeDisposable.add(playersRepositoryImpl.getPlayerDetail(idPlayer)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeWith(object: ResourceSubscriber<PlayerMatchDetail>(){
                    override fun onComplete() {

                    }

                    override fun onNext(t: PlayerMatchDetail) {
                        mView.viewPlayerDetail(t.player[0])
                    }

                    override fun onError(t: Throwable?) {

                    }

                })
        )
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }

}