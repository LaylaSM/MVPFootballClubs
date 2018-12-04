package com.laylasm.mvpfootballclubs.presenter

import com.laylasm.mvpfootballclubs.model.MatchRepoImp
import com.laylasm.mvpfootballclubs.model.SchedulerProvider
import com.laylasm.mvpfootballclubs.model.SearchMatchClub
import com.laylasm.mvpfootballclubs.model.SearchMatchView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class SearchMatchPresenter(val mView: SearchMatchView.View,
       val matchRepositoryImpl: MatchRepoImp,
       val schedulerProvider: SchedulerProvider): SearchMatchView.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun searchMatch(query: String?) {
        mView.showLoading()
        compositeDisposable.add(matchRepositoryImpl.searchMatches(query)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeWith(object: ResourceSubscriber<SearchMatchClub>(){
                    override fun onComplete() {
                        mView.hideLoading()
                    }

                    override fun onNext(t: SearchMatchClub) {
                        mView.viewMatch(t.events ?: Collections.emptyList())
                    }

                    override fun onError(t: Throwable?) {
                        mView.viewMatch(Collections.emptyList())
                        mView.hideLoading()
                    }

                })
        )
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }



}