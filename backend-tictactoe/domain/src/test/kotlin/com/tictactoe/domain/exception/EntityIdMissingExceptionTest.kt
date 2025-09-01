package com.tictactoe.domain.exception

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class EntityIdMissingExceptionTest {
    @Test
    fun `entity property should contain the correct value`() {
        val ex = EntityIdMissingException("test entity")
        assertEquals("test entity", ex.entity)
    }

    @Test
    fun `exception message should contain the parameter name`() {
        val ex = EntityIdMissingException("entity")
        assertTrue(ex.message!!.contains("entity"))
        assertEquals("The entity entity must have an ID", ex.message)
    }
}