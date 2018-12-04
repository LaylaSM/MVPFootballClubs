package com.laylasm.mvpfootballclubs.model

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class PlayerMatchRepoImpTest {

    @Mock
    lateinit var footballMatchEndPoint: FootballMatchEndPoint
    lateinit var playerMatchRepoImp: PlayerMatchRepoImp

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        playerMatchRepoImp = PlayerMatchRepoImp(footballMatchEndPoint)
    }

    @Test
    fun getAllPlayers() {
        playerMatchRepoImp.getAllPlayers("456")
        verify(footballMatchEndPoint).getAllPlayers("456")
    }

    @Test
    fun getPlayerDetail() {
        playerMatchRepoImp.getPlayerDetail("456")
        verify(footballMatchEndPoint).getPlayerDetail("456")
    }
}