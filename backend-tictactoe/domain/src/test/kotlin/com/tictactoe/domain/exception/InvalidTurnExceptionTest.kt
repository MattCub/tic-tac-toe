package com.tictactoe.domain.exception

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class InvalidTurnExceptionTest {
    @Test
    fun `exception message should contain the correct value`() {
        val ex = InvalidTurnException("Invalid turn")
        assertEquals("Invalid turn", ex.message)
    }
}