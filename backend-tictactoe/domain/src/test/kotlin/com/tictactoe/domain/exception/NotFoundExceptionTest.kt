package com.tictactoe.domain.exception

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class NotFoundExceptionTest {
    @Test
    fun `entity and id properties should contain the correct values`() {
        val ex = NotFoundException("Match", "123")
        assertEquals("Match", ex.entity)
        assertEquals("123", ex.id)
    }

    @Test
    fun `exception message should contain entity and id`() {
        val ex = NotFoundException("Match", "123")
        assertEquals("The entity Match with id 123 was not found", ex.message)
    }
}