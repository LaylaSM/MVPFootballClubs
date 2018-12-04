package com.laylasm.mvpfootballclubs.presenter

import com.laylasm.mvpfootballclubs.model.FootballMatchResponse
import com.laylasm.mvpfootballclubs.model.MatchRepoImp
import com.laylasm.mvpfootballclubs.model.MatchView
import com.laylasm.mvpfootballclubs.model.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class PrevMatchPresenter(
        val mView: MatchView.View,
        val matchRepositoryImpl: MatchRepoImp,
        val scheduler: SchedulerProvider) : MatchView.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getFootballMatchData(clubName: String) {
        mView.showLoading()
        compositeDisposable.add(matchRepositoryImpl.getFootballMatch(clubName)
                .observeOn(scheduler.ui())
                .subscribeOn(scheduler.io())
                .subscribeWith(object : ResourceSubscriber<FootballMatchResponse>() {
                    override fun onComplete() {
                        mView.hideLoading()
                    }

                    override fun onNext(t: FootballMatchResponse) {
                        mView.displayFootballMatch(t.events)
                    }

                    override fun onError(e: Throwable) {
                        mView.hideLoading()
                        mView.displayFootballMatch(Collections.emptyList())
                    }

                })
        )
    }

    override fun onDestroyPresenter() {
        compositeDisposable.dispose()
    }
}