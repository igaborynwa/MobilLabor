package com.example.mobillabor.test

import com.example.mobillabor.model.Player
import com.example.mobillabor.testInjector
import com.example.mobillabor.ui.player.PlayerPresenter
import com.example.mobillabor.ui.player.PlayerScreen
import com.example.mobillabor.utils.argumentCaptor
import com.example.mobillabor.utils.mock
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.verify
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class PlayerTest {

   @Inject
    lateinit var playerPresenter: PlayerPresenter

    private lateinit var playerScreen: PlayerScreen

    @Before
    fun setup() {
        testInjector.inject(this)
        playerScreen = mock()
        playerPresenter.attachScreen(playerScreen)
    }

    @Test
    fun testPlayerDetailsFromDB() {
        playerPresenter.getPlayer(3754, false)
        val player = argumentCaptor<Player>()
        verify(playerScreen).showPlayer(player.capture())
        assert(player.value.id == 3754)
        assert(player.value.name == "Mohamed Salah")
    }

    @Test
    fun testPlayerDetailsFromApi() {
        playerPresenter.getPlayer(3754, true)
        val player = argumentCaptor<Player>()
        verify(playerScreen).showPlayer(player.capture())
        assert(player.value.id == 3754)
        assert(player.value.name == "Mohamed Salah")
    }





}