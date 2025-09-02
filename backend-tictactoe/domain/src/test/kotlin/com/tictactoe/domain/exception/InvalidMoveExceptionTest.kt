package com.tictactoe.domain.exception

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class InvalidMoveExceptionTest {
    @Test
    fun `exception message should contain the correct value`() {
        val ex = InvalidMoveException("Invalid move")
        assertEquals("Invalid move", ex.message)
    }
}