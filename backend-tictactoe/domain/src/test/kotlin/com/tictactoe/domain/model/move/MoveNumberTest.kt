package com.tictactoe.domain.model.move

import com.tictactoe.domain.exception.InvalidParameterException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class MoveNumberTest {
    @Test
    fun `should create MoveNumber successfully`() {
        val moveNumber = MoveNumber.create(5)
        assertEquals(5, moveNumber.value)
    }

    @Test
    fun `should throw exception if number is less than 1`() {
        assertThrows(InvalidParameterException::class.java) {
            MoveNumber.create(0)
        }
    }

    @Test
    fun `should throw exception if number is greater than 9`() {
        assertThrows(InvalidParameterException::class.java) {
            MoveNumber.create(10)
        }
    }
}