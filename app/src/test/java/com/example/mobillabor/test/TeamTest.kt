package com.example.mobillabor.test

import com.example.mobillabor.model.Player
import com.example.mobillabor.model.Team
import com.example.mobillabor.testInjector
import com.example.mobillabor.ui.team.TeamPresenter
import com.example.mobillabor.ui.team.TeamScreen
import com.example.mobillabor.utils.argumentCaptor
import com.example.mobillabor.utils.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.atLeastOnce
import org.mockito.Mockito.timeout
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class TeamTest {
    @Inject
    lateinit var teamPresenter: TeamPresenter

    lateinit var teamScreen: TeamScreen

    @Before
    fun setup() {
        testInjector.inject(this)
        teamScreen = mock()
        teamPresenter.attachScreen(teamScreen)
    }

    @Test
    fun testGetTeamFromDB() {
        runBlocking {
            launch(Dispatchers.Main) {
                teamPresenter.getTeam(false)
                val team = argumentCaptor<Team>()
                Mockito.verify(teamScreen).showTeam(team.capture())
                assert(team.value.name == "Liverpool FC")
            }
        }
    }

    @Test
    fun testGetTeamFromApi() {
        runBlocking {
            launch(Dispatchers.Main) {
                teamPresenter.getTeam(true)
                val team = argumentCaptor<Team>()
                Mockito.verify(teamScreen, timeout(3000)).showTeam(team.capture())
                assert(team.value.name == "Liverpool FC")
            }
        }
    }

    @Test
    fun testDeletePlayer() {
        val salah = Player(3754, "Mohamed Salah", "1992-06-15", "Egypt", "Egypt", "Attacker")
        teamPresenter.deletePlayer(salah)
        val name = argumentCaptor<String>()
        Mockito.verify(teamScreen).showDeleteSuccess(name.capture())
        assert(name.value == salah.name)

    }


    @Test
    fun testAddPlayer() {
        val salah = Player(3754, "Mohamed Salah", "1992-06-15", "Egypt", "Egypt", "Attacker")
        teamPresenter.addPlayer(salah)
        val playerId = argumentCaptor<Int>()
        Mockito.verify(teamScreen).showPlayerAdded(playerId.capture())
        assert(playerId.value == 3754)
    }


    @Test
    fun testModifyPlayer() {
        val salah = Player(3754, "Mohamed Salah", "1992-06-15", "Egypt", "Egypt", "Attacker")
        teamPresenter.modifyPlayer(salah)
        val player = argumentCaptor<Player>()
        Mockito.verify(teamScreen).showPlayerModified(player.capture())
        assert(player.value.name == "Mohamed Salah")
    }

    @After
    fun tearDown() {
        teamPresenter.detachScreen()
    }

}