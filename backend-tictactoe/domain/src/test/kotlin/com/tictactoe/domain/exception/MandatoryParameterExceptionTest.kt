package com.tictactoe.domain.exception

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MandatoryParameterExceptionTest {

    @Test
    fun `parameter property should contain the correct value`() {
        val ex = MandatoryParameterException("id")
        assertEquals("id", ex.parameter)
    }

    @Test
    fun `exception message should contain the parameter name`() {
        val ex = MandatoryParameterException("name")
        assertTrue(ex.message!!.contains("name"))
        assertEquals("The parameter name is mandatory", ex.message)
    }
}