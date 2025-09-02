package com.tictactoe.domain.model.move

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MoveIdTest {
    @Test
    fun `should create MoveId successfully`() {
        val moveId = MoveId.create(1L)
        assertEquals(1L, moveId.value)
    }
}