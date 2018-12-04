package com.laylasm.mvpfootballclubs.presenter

import com.laylasm.mvpfootballclubs.model.*
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class NextMatchPresenterTest {

    @Mock
    lateinit var matchView : MatchView.View

    @Mock
    lateinit var matchRepoImp: MatchRepoImp
    lateinit var schedulerProvider: SchedulerProvider
    lateinit var mPresenter: NextMatchPresenter
    lateinit var footballMatchResponse: FootballMatchResponse
    lateinit var footballMatch : Flowable<FootballMatchResponse>
    private val data = mutableListOf<Data>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        schedulerProvider = TestSchedulerProvider()
        footballMatchResponse = FootballMatchResponse(data)
        footballMatch = Flowable.just(footballMatchResponse)
        mPresenter = NextMatchPresenter(matchView, matchRepoImp, schedulerProvider)
        Mockito.`when` (matchRepoImp.getNextMatch("4328")).thenReturn(footballMatch)
    }


    @Test
    fun getFootballMatchData() {
        mPresenter.getFootballMatchData()
        Mockito.verify(matchView).showLoading()
        Mockito.verify(matchView).displayFootballMatch(data)
        Mockito.verify(matchView).hideLoading()
    }


}