package com.laylasm.mvpfootballclubs.presenter

import com.laylasm.mvpfootballclubs.model.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class FavMatchPresenter(val mView: FavMatchView.View,
                        val matchRepositoryImpl: MatchRepoImp,
                        val localRepositoryImpl: LocalRepoImp,
                        val scheduler: SchedulerProvider) : FavMatchView.Presenter{

    val compositeDisposable = CompositeDisposable()

    override fun getFootballMatchData() {
        mView.showLoading()
        val favList = localRepositoryImpl.getMatchFromDb()
        var eventList: MutableList<Data> = mutableListOf()
        for (fav in favList){
            compositeDisposable.add(matchRepositoryImpl.getEventById(fav.idEvent)
                    .observeOn(scheduler.ui())
                    .subscribeOn(scheduler.io())
                    .subscribeWith(object: ResourceSubscriber<FootballMatchResponse>(){
                        override fun onComplete() {
                            mView.hideLoading()
                            mView.hideSwipeRefresh()
                        }

                        override fun onNext(t: FootballMatchResponse) {
                            eventList.add(t.events[0])
                            mView.displayFootballMatch(eventList)
                        }

                        override fun onError(t: Throwable?) {
                            mView.displayFootballMatch(Collections.emptyList())
                            mView.hideLoading()
                            mView.hideSwipeRefresh()
                        }

                    })
            )
        }

        if(favList.isEmpty()){
            mView.hideLoading()
            mView.hideSwipeRefresh()
            mView.displayFootballMatch(eventList)
        }
    }

    override fun onDestroyPresenter() {
        compositeDisposable.dispose()
    }
}