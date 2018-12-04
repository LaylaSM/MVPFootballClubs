package com.laylasm.mvpfootballclubs.presenter

import com.laylasm.mvpfootballclubs.model.FootballMatchPlayer
import com.laylasm.mvpfootballclubs.model.PlayerMatchRepoImp
import com.laylasm.mvpfootballclubs.model.PlayerMatchView
import com.laylasm.mvpfootballclubs.model.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class PlayerMatchPresenter (val mView: PlayerMatchView.View,
                            val playersRepositoryImpl: PlayerMatchRepoImp,
                            val schedulerProvider: SchedulerProvider): PlayerMatchView.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getAllPlayer(teamId: String?) {
        mView.showLoading()
        compositeDisposable.add(playersRepositoryImpl.getAllPlayers(teamId)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeWith(object : ResourceSubscriber<FootballMatchPlayer>() {
                    override fun onComplete() {
                        mView.hideLoading()
                    }

                    override fun onNext(t: FootballMatchPlayer) {
                        mView.displayPlayers(t.player)
                    }

                    override fun onError(t: Throwable?) {
                        mView.displayPlayers(Collections.emptyList())
                        mView.hideLoading()
                    }

                })
        )
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}