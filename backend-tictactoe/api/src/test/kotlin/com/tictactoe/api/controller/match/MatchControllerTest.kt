package com.tictactoe.api.controller.match

import com.tictactoe.api.dto.createMatch.CreateMatchResponseDTO
import com.tictactoe.domain.model.match.MatchId
import com.tictactoe.domain.service.match.CreateMatchUseCase
import com.tictactoe.domain.service.move.CreateMoveUseCase
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.ResponseEntity

class MatchControllerTest {

    private val mockCreateMatchUseCase = Mockito.mock(CreateMatchUseCase::class.java)
    private val mockCreateMoveUseCase = Mockito.mock(CreateMoveUseCase::class.java)
    private val matchController = MatchController(mockCreateMatchUseCase, mockCreateMoveUseCase)

    @Test
    fun `create should return ResponseEntity with correct DTO`() {
        val matchId = MatchId.create(123L)
        Mockito.`when`(mockCreateMatchUseCase.execute()).thenReturn(matchId)

        val response: ResponseEntity<CreateMatchResponseDTO> = matchController.create()

        assertEquals(200, response.statusCode.value())
        assertEquals(CreateMatchResponseDTO(123L), response.body)
    }

}
