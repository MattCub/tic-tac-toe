package com.tictactoe.application.usecase

import com.tictactoe.domain.exception.NotFoundException
import com.tictactoe.domain.model.match.Match
import com.tictactoe.domain.model.match.MatchId
import com.tictactoe.domain.model.match.MatchStatus
import com.tictactoe.domain.model.move.BoardSummary
import com.tictactoe.domain.model.move.Move
import com.tictactoe.domain.repositories.match.MatchRepository
import com.tictactoe.domain.repositories.move.MoveRepository
import com.tictactoe.domain.service.match.params.GetStatusResult
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
class GetStatusUseCaseImplTest {
    private val matchId = MatchId.create(1L)
    private val match = Match.Builder().id(matchId).status(MatchStatus.IN_PROGRESS).build()
    private val moves = listOf<Move>()
    private val boardSummary = BoardSummary.fromMoves(moves)

    @Mock
    private lateinit var matchRepository: MatchRepository

    @Mock
    private lateinit var moveRepository: MoveRepository

    @InjectMocks
    private lateinit var useCase: GetStatusUseCaseImpl

    @Test
    fun `Should get status successfully`() {
        whenever(matchRepository.findById(matchId.value)).thenReturn(match)
        whenever(moveRepository.findByMatchId(matchId.value)).thenReturn(moves)

        val result: GetStatusResult = useCase.execute(matchId)

        assertEquals(match.status, result.status)
        assertEquals(boardSummary.getCurrentTurn(), result.currentTurn)
        assertEquals(boardSummary.matrix, result.overall.matrix)
        assertEquals(boardSummary.totalMoves, result.overall.totalMoves)
        verify(matchRepository).findById(matchId.value)
        verify(moveRepository).findByMatchId(matchId.value)
    }

    @Test
    fun `Should throw NotFoundException when match does not exist`() {
        whenever(matchRepository.findById(matchId.value)).thenReturn(null)

        val exception = assertThrows(NotFoundException::class.java) {
            useCase.execute(matchId)
        }
        assertEquals("Match", exception.entity)
        assertEquals(matchId.value.toString(), exception.id)
        verify(matchRepository).findById(matchId.value)
    }
}