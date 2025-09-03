package com.tictactoe.domain.model.move

import com.tictactoe.domain.exception.InvalidParameterException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class MovePositionTest {
    
    @Test
    fun `should create MovePosition successfully`() {
        val movePosition = MovePosition.create(2)
        assertEquals(2, movePosition.value)
    }

    @Test
    fun `should throw exception if position is less than 1`() {
        assertThrows(InvalidParameterException::class.java) {
            MovePosition.create(0)
        }
    }

    @Test
    fun `should throw exception if position is greater than 3`() {
        assertThrows(InvalidParameterException::class.java) {
            MovePosition.create(4)
        }
    }
}