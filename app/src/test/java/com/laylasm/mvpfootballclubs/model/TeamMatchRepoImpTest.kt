package com.laylasm.mvpfootballclubs.model

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class TeamMatchRepoImpTest {

    @Mock
    lateinit var footballMatchEndPoint: FootballMatchEndPoint
    lateinit var teamMatchRepoImp: TeamMatchRepoImp

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        teamMatchRepoImp = TeamMatchRepoImp(footballMatchEndPoint)
    }

    @Test
    fun getAllTeam() {
        teamMatchRepoImp.getAllTeam("id")
        verify(footballMatchEndPoint).getAllTeam("id")
    }

    @Test
    fun getTeams() {
        teamMatchRepoImp.getTeams("111")
        verify(footballMatchEndPoint).getAllTeam("111")
    }

    @Test
    fun getTeamBySearch() {
        teamMatchRepoImp.getTeamBySearch("query")
        verify(footballMatchEndPoint).getTeamBySearch("query")
    }

    @Test
    fun getTeamsDetail() {
        teamMatchRepoImp.getTeamsDetail("id")
        verify(footballMatchEndPoint).getTeam("id")
    }
}