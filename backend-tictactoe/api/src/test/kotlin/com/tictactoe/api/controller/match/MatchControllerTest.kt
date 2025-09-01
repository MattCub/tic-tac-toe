package com.tictactoe.api.controller.match

import com.tictactoe.api.dto.createMatch.CreateMatchResponseDTO
import com.tictactoe.domain.model.match.MatchId
import com.tictactoe.domain.service.match.CreateMatchUseCase
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.ResponseEntity

class MatchControllerTest {

    private val createMatchUseCase = Mockito.mock(CreateMatchUseCase::class.java)
    private val matchController = MatchController(createMatchUseCase)

    @Test
    fun `create should return ResponseEntity with correct DTO`() {
        val matchId = MatchId.create(123L)
        Mockito.`when`(createMatchUseCase.execute()).thenReturn(matchId)

        val response: ResponseEntity<CreateMatchResponseDTO> = matchController.create()

        assertEquals(200, response.statusCode.value())
        assertEquals(CreateMatchResponseDTO(123L), response.body)
    }

}
