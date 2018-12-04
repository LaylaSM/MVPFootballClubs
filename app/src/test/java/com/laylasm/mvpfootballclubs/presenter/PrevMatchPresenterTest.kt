package com.laylasm.mvpfootballclubs.presenter

import com.laylasm.mvpfootballclubs.model.*
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class PrevMatchPresenterTest {

    @Mock
    lateinit var matchView: MatchView.View

    @Mock
    lateinit var matchRepoImp: MatchRepoImp
    lateinit var schedulerProvider: SchedulerProvider
    lateinit var mPresenter: PrevMatchPresenter
    lateinit var footballMatchResponse: FootballMatchResponse
    lateinit var footballMatch: Flowable<FootballMatchResponse>
    private val data = mutableListOf<Data>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        schedulerProvider = TestSchedulerProvider()
        footballMatchResponse = FootballMatchResponse(data)
        footballMatch = Flowable.just(footballMatchResponse)
        mPresenter = PrevMatchPresenter(matchView, matchRepoImp, schedulerProvider)
        `when` (matchRepoImp.getFootballMatch("4328")).thenReturn(footballMatch)
    }


    @Test
    fun getFootballMatchData() {
        mPresenter.getFootballMatchData()
        verify(matchView).showLoading()
        verify(matchView).displayFootballMatch(data)
        verify(matchView).hideLoading()
    }

}