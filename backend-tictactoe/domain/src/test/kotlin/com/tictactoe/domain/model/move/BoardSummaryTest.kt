package com.tictactoe.domain.model.move

import com.tictactoe.domain.model.match.Match
import com.tictactoe.domain.model.match.MatchId
import com.tictactoe.domain.model.player.Player
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BoardSummaryTest {
    private val playerX = Player.create("X")
    private val playerO = Player.create("O")
    private val match = Match.Builder().id(MatchId.create(1)).build()

    @Test
    fun `matrix is empty when no moves`() {
        val board = BoardSummary.fromMoves(emptyList())
        assertEquals(0, board.totalMoves)
        assertTrue(board.matrix.all { row -> row.all { it == null } })
    }

    @Test
    fun `matrix is full when all cells are occupied`() {
        val moves = listOf(
            Move.Builder().match(match).x(MovePosition.create(1)).y(MovePosition.create(1)).player(playerX)
                .moveNumber(MoveNumber.create(1)).build(),
            Move.Builder().match(match).x(MovePosition.create(1)).y(MovePosition.create(2)).player(playerO)
                .moveNumber(MoveNumber.create(2)).build(),
            Move.Builder().match(match).x(MovePosition.create(1)).y(MovePosition.create(3)).player(playerX)
                .moveNumber(MoveNumber.create(3)).build(),
            Move.Builder().match(match).x(MovePosition.create(2)).y(MovePosition.create(1)).player(playerO)
                .moveNumber(MoveNumber.create(4)).build(),
            Move.Builder().match(match).x(MovePosition.create(2)).y(MovePosition.create(2)).player(playerX)
                .moveNumber(MoveNumber.create(5)).build(),
            Move.Builder().match(match).x(MovePosition.create(2)).y(MovePosition.create(3)).player(playerO)
                .moveNumber(MoveNumber.create(6)).build(),
            Move.Builder().match(match).x(MovePosition.create(3)).y(MovePosition.create(1)).player(playerO)
                .moveNumber(MoveNumber.create(7)).build(),
            Move.Builder().match(match).x(MovePosition.create(3)).y(MovePosition.create(2)).player(playerX)
                .moveNumber(MoveNumber.create(8)).build(),
            Move.Builder().match(match).x(MovePosition.create(3)).y(MovePosition.create(3)).player(playerO)
                .moveNumber(MoveNumber.create(9)).build()
        )
        val board = BoardSummary.fromMoves(moves)
        assertEquals(9, board.totalMoves)
        assertTrue(board.matrix.all { row -> row.all { it != null } })
    }

    @Test
    fun `matrix reflects correct player positions`() {
        val moves = listOf(
            Move.Builder().match(match).x(MovePosition.create(1)).y(MovePosition.create(1)).player(playerX)
                .moveNumber(MoveNumber.create(1)).build(),
            Move.Builder().match(match).x(MovePosition.create(1)).y(MovePosition.create(3)).player(playerO)
                .moveNumber(MoveNumber.create(2)).build(),
            Move.Builder().match(match).x(MovePosition.create(2)).y(MovePosition.create(2)).player(playerO)
                .moveNumber(MoveNumber.create(3)).build(),
            Move.Builder().match(match).x(MovePosition.create(3)).y(MovePosition.create(1)).player(playerX)
                .moveNumber(MoveNumber.create(4)).build()
        )
        val board = BoardSummary.fromMoves(moves)
        assertEquals(playerX, board.matrix[0][0])
        assertEquals(playerO, board.matrix[0][2])
        assertEquals(playerO, board.matrix[1][1])
        assertEquals(playerX, board.matrix[2][0])
        assertNull(board.matrix[1][0])
    }
}
