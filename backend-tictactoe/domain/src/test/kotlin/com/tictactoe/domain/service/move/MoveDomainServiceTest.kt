package com.tictactoe.domain.service.move

import com.tictactoe.domain.exception.InvalidMoveException
import com.tictactoe.domain.model.match.Match
import com.tictactoe.domain.model.match.MatchId
import com.tictactoe.domain.model.match.MatchStatus
import com.tictactoe.domain.model.move.BoardSummary
import com.tictactoe.domain.model.move.Move
import com.tictactoe.domain.model.move.MoveNumber
import com.tictactoe.domain.model.move.MovePosition
import com.tictactoe.domain.model.player.Player
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MoveDomainServiceTest {
    private val match = Match.Builder().id(MatchId.create(1L)).status(MatchStatus.IN_PROGRESS).build()
    private val service = MoveDomainService()
    private val playerX = Player.create("X")
    private val playerO = Player.create("O")

    private fun move(x: Int, y: Int, player: Player, moveNumber: Int = 1) = Move(
        id = null,
        match = match,
        player = player,
        x = MovePosition.create(x),
        y = MovePosition.create(y),
        moveNumber = MoveNumber.create(moveNumber),
        createdAt = java.time.LocalDateTime.now()
    )

    @Test
    fun `validateMove throws exception if cell is occupied`() {
        val moves = listOf(move(2, 2, playerX, 1))
        val exception = assertThrows(InvalidMoveException::class.java) {
            service.validateMove(
                moves,
                MovePosition.create(2),
                MovePosition.create(2),
                playerX,
                MatchStatus.IN_PROGRESS
            )
        }
        assertEquals("The square is already occupied.", exception.message)
    }

    @Test
    fun `validateMove throws exception if board is full`() {
        val moves = (1..3).flatMap { x -> (1..3).map { y -> move(x, y, playerX, ((x - 1) * 3 + y)) } }
        val exception = assertThrows(InvalidMoveException::class.java) {
            service.validateMove(
                moves,
                MovePosition.create(2),
                MovePosition.create(2),
                playerX,
                MatchStatus.IN_PROGRESS
            )
        }
        assertEquals("The match is over. No more moves allowed.", exception.message)
    }

    @Test
    fun `validateMove throws exception if match is in status ENDED`() {
        val moves = (1..3).flatMap { x -> (1..3).map { y -> move(x, y, playerX, ((x - 1) * 3 + y)) } }
        val exception = assertThrows(InvalidMoveException::class.java) {
            service.validateMove(
                moves,
                MovePosition.create(2),
                MovePosition.create(2),
                playerX,
                MatchStatus.ENDED
            )
        }
        assertEquals("The match is over.", exception.message)
    }

    @Test
    fun `validateMove does not throw for valid move`() {
        val moves = listOf(move(1, 1, playerX, 1))
        assertDoesNotThrow {
            service.validateMove(
                moves,
                MovePosition.create(1),
                MovePosition.create(3),
                playerO,
                MatchStatus.IN_PROGRESS
            )
        }
    }

    @Test
    fun `checkGameStatus detects horizontal win`() {
        val board = BoardSummary.fromMoves(
            listOf(
                Move.Builder().match(match).x(MovePosition.create(1)).y(MovePosition.create(1)).player(playerX)
                    .moveNumber(MoveNumber.create(1)).build(),
                Move.Builder().match(match).x(MovePosition.create(1)).y(MovePosition.create(2)).player(playerX)
                    .moveNumber(MoveNumber.create(2)).build(),
                Move.Builder().match(match).x(MovePosition.create(1)).y(MovePosition.create(3)).player(playerX)
                    .moveNumber(MoveNumber.create(3)).build()
            )
        )
        val (status, winner) = service.checkGameStatus(board)
        assertEquals(MatchStatus.ENDED, status)
        assertEquals(playerX, winner)
    }

    @Test
    fun `checkGameStatus detects vertical win`() {
        val board = BoardSummary.fromMoves(
            listOf(
                Move.Builder().match(match).x(MovePosition.create(1)).y(MovePosition.create(1)).player(playerO)
                    .moveNumber(MoveNumber.create(1)).build(),
                Move.Builder().match(match).x(MovePosition.create(2)).y(MovePosition.create(1)).player(playerO)
                    .moveNumber(MoveNumber.create(2)).build(),
                Move.Builder().match(match).x(MovePosition.create(3)).y(MovePosition.create(1)).player(playerO)
                    .moveNumber(MoveNumber.create(3)).build()
            )
        )
        val (status, winner) = service.checkGameStatus(board)
        assertEquals(MatchStatus.ENDED, status)
        assertEquals(playerO, winner)
    }

    @Test
    fun `checkGameStatus detects diagonal win`() {
        val board = BoardSummary.fromMoves(
            listOf(
                Move.Builder().match(match).x(MovePosition.create(1)).y(MovePosition.create(1)).player(playerX)
                    .moveNumber(MoveNumber.create(1)).build(),
                Move.Builder().match(match).x(MovePosition.create(2)).y(MovePosition.create(2)).player(playerX)
                    .moveNumber(MoveNumber.create(2)).build(),
                Move.Builder().match(match).x(MovePosition.create(3)).y(MovePosition.create(3)).player(playerX)
                    .moveNumber(MoveNumber.create(3)).build()
            )
        )
        val (status, winner) = service.checkGameStatus(board)
        assertEquals(MatchStatus.ENDED, status)
        assertEquals(playerX, winner)
    }

    @Test
    fun `checkGameStatus detects draw`() {
        val board = BoardSummary.fromMoves(
            listOf(
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
        )
        val (status, winner) = service.checkGameStatus(board)
        assertEquals(MatchStatus.ENDED, status)
        assertNull(winner)
    }

    @Test
    fun `checkGameStatus detects in progress`() {
        val board = BoardSummary.fromMoves(
            listOf(
                Move.Builder().match(match).x(MovePosition.create(1)).y(MovePosition.create(1)).player(playerX)
                    .moveNumber(MoveNumber.create(1)).build(),
                Move.Builder().match(match).x(MovePosition.create(2)).y(MovePosition.create(2)).player(playerO)
                    .moveNumber(MoveNumber.create(2)).build()
            )
        )
        val (status, winner) = service.checkGameStatus(board)
        assertEquals(MatchStatus.IN_PROGRESS, status)
        assertNull(winner)
    }
}
