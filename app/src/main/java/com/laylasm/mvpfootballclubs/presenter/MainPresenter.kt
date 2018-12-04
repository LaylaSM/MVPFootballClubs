package com.laylasm.mvpfootballclubs.presenter

import com.laylasm.mvpfootballclubs.model.MainView
import com.laylasm.mvpfootballclubs.model.MatchRepoImp
import io.reactivex.disposables.CompositeDisposable

class MainPresenter(val mView : MainView.View, val matchRepositoryImpl: MatchRepoImp) :
        MainView.Presenter{

    val compositeDisposable = CompositeDisposable()
}