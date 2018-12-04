package com.laylasm.mvpfootballclubs.model

import org.junit.Before
import org.junit.Test

import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


class MatchRepoImpTest {

    @Mock
    lateinit var footballMatchEndPoint : FootballMatchEndPoint
    lateinit var matchRepoImp: MatchRepoImp


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        matchRepoImp = MatchRepoImp(footballMatchEndPoint)
    }

    @Test
    fun getEventById() {
        matchRepoImp.getEventById("123")
        verify(footballMatchEndPoint).getEventById("123")
    }

    @Test
    fun getNextMatch() {
        matchRepoImp.getNextMatch("123")
        verify(footballMatchEndPoint).getNextMatch("123")
    }

    @Test
    fun getFootballMatch() {
        matchRepoImp.getFootballMatch("123")
        verify(footballMatchEndPoint).getPrevMatch("123")
    }


}