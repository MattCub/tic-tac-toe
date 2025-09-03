package com.tictactoe.domain.exception

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class InvalidParameterExceptionTest {

    @Test
    fun `exception message should be equals`() {
        val ex = InvalidParameterException("Test error message")
        assertEquals("Test error message", ex.message)
    }
}