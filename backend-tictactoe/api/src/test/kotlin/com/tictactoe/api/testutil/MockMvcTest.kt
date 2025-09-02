package com.tictactoe.api.testutil

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.tictactoe.api.exception.GlobalExceptionHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.RequestBuilder
import org.springframework.test.web.servlet.ResultActions

@ContextConfiguration(classes = [GlobalExceptionHandler::class])
abstract class MockMvcTest {
    @Autowired
    protected lateinit var mockMvc: MockMvc

    protected val objectMapper = jacksonObjectMapper()

    fun perform(requestBuilder: RequestBuilder): ResultActions {
        return mockMvc.perform(requestBuilder)
    }
}