package com.laylasm.mvpfootballclubs.presenter

import com.laylasm.mvpfootballclubs.model.*
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class PlayerMatchPresenterTest {

    @Mock
    lateinit var matchView: PlayerMatchView.View

    @Mock
    lateinit var playerMatchRepoImp: PlayerMatchRepoImp
    lateinit var schedulerProvider: SchedulerProvider
    lateinit var mPresenter: PlayerMatchPresenter
    lateinit var footballMatchPlayer: FootballMatchPlayer
    lateinit var footballMatchDetail: Flowable<FootballMatchPlayer>
    private val footballMatchPlayerList = mutableListOf<PlayerMatch>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        schedulerProvider = TestSchedulerProvider()
        footballMatchPlayer = FootballMatchPlayer(footballMatchPlayerList)
        footballMatchDetail = Flowable.just(footballMatchPlayer)
        mPresenter = PlayerMatchPresenter(matchView, playerMatchRepoImp, schedulerProvider)
        `when`(playerMatchRepoImp.getAllPlayers("111")).thenReturn(footballMatchDetail)
    }


    @Test
    fun getAllPlayer() {
        mPresenter.getAllPlayer("111")
        verify(matchView).showLoading()
        verify(matchView).displayPlayers(footballMatchPlayerList)
        verify(matchView).hideLoading()
    }

}