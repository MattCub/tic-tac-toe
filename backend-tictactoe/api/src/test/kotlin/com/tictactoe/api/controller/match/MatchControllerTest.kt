package com.tictactoe.api.controller.match

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.tictactoe.api.dto.createMove.CreateMoveRequestDTO
import com.tictactoe.api.testutil.MockMvcTest
import com.tictactoe.domain.exception.InvalidMoveException
import com.tictactoe.domain.exception.InvalidTurnException
import com.tictactoe.domain.exception.NotFoundException
import com.tictactoe.domain.model.match.Match
import com.tictactoe.domain.model.match.MatchId
import com.tictactoe.domain.model.match.MatchStatus
import com.tictactoe.domain.model.move.Move
import com.tictactoe.domain.model.move.MoveId
import com.tictactoe.domain.model.move.MoveNumber
import com.tictactoe.domain.model.move.MovePosition
import com.tictactoe.domain.model.player.Player
import com.tictactoe.domain.service.match.CreateMatchUseCase
import com.tictactoe.domain.service.move.CreateMoveUseCase
import com.tictactoe.domain.service.move.params.CreateMoveResult
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest
@ContextConfiguration(classes = [MatchController::class, com.tictactoe.api.exception.GlobalExceptionHandler::class])
class MatchControllerTest : MockMvcTest() {

    @MockitoBean
    private lateinit var mockCreateMatchUseCase: CreateMatchUseCase

    @MockitoBean
    private lateinit var mockCreateMoveUseCase: CreateMoveUseCase


    @Test
    fun `create should return ResponseEntity with correct DTO`() {
        val matchId = MatchId.create(123L)
        Mockito.`when`(mockCreateMatchUseCase.execute()).thenReturn(matchId)

        perform(MockMvcRequestBuilders.post("/match/create"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.matchId").value(123L))
    }

    @Test
    fun `createMove should return ResponseEntity with correct DTO`() {
        val matchId = 123L
        val request = CreateMoveRequestDTO(
            playerId = "x",
            square = CreateMoveRequestDTO.SquareDTO(x = 1, y = 2)
        )
        val match = Match.Builder()
            .id(MatchId.create(matchId))
            .status(MatchStatus.IN_PROGRESS)
            .build()
        val move = Move.Builder()
            .id(MoveId.create(1L))
            .match(match)
            .player(Player.create("x"))
            .x(MovePosition.create(1))
            .y(MovePosition.create(2))
            .moveNumber(MoveNumber.create(1))
            .build()
        val moves = listOf(move)
        val expectedResult = CreateMoveResult.Builder()
            .moveId(MoveId.create(1L))
            .status(MatchStatus.IN_PROGRESS)
            .winner(Player.create("X"))
            .boardSummary(moves)
            .build()
        Mockito.`when`(mockCreateMoveUseCase.execute(any())).thenReturn(expectedResult)

        perform(
            MockMvcRequestBuilders.post("/match/{matchId}/move", matchId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.moveId").value(1L))
    }

    @Test
    fun `createMove should return 404 when NotFoundException is thrown`() {
        val matchId = 123L
        val request = CreateMoveRequestDTO(
            playerId = "x",
            square = CreateMoveRequestDTO.SquareDTO(x = 1, y = 2)
        )
        Mockito.`when`(mockCreateMoveUseCase.execute(any())).thenThrow(NotFoundException("Match", matchId.toString()))

        perform(
            MockMvcRequestBuilders.post("/match/{matchId}/move", matchId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Not Found"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("The entity Match with id 123 was not found"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.exception").value("NotFoundException"))
    }

    @Test
    fun `createMove should return 409 when InvalidTurnException is thrown`() {
        val matchId = 123L
        val request = CreateMoveRequestDTO(
            playerId = "x",
            square = CreateMoveRequestDTO.SquareDTO(x = 1, y = 2)
        )
        Mockito.`when`(mockCreateMoveUseCase.execute(any())).thenThrow(InvalidTurnException("Invalid turn"))

        perform(
            MockMvcRequestBuilders.post("/match/{matchId}/move", matchId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(MockMvcResultMatchers.status().isConflict)
            .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Conflict"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Invalid turn"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.exception").value("InvalidTurnException"))
    }

    @Test
    fun `createMove should return 409 when InvalidMoveException is thrown`() {
        val matchId = 123L
        val request = CreateMoveRequestDTO(
            playerId = "x",
            square = CreateMoveRequestDTO.SquareDTO(x = 1, y = 2)
        )
        Mockito.`when`(mockCreateMoveUseCase.execute(any())).thenThrow(InvalidMoveException("Invalid move"))

        perform(
            MockMvcRequestBuilders.post("/match/{matchId}/move", matchId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(MockMvcResultMatchers.status().isConflict)
            .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Conflict"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Invalid move"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.exception").value("InvalidMoveException"))
    }
}
