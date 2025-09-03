package com.tictactoe.domain.model.player

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import com.tictactoe.domain.exception.MandatoryParameterException

class PlayerTest {
    @Test
    fun `should create valid player`() {
        val player = Player.create("O")
        assertEquals("O", player.id)
    }

    @Test
    fun `should throw exception`() {
        val exception = assertThrows(MandatoryParameterException::class.java) {
            Player.create("")
        }
        assertEquals("The parameter id is mandatory", exception.message)
    }
}