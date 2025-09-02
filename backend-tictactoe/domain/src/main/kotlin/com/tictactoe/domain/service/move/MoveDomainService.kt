package com.tictactoe.domain.service.move

import com.tictactoe.domain.exception.InvalidMoveException
import com.tictactoe.domain.exception.InvalidTurnException
import com.tictactoe.domain.model.match.MatchStatus
import com.tictactoe.domain.model.move.BoardSummary
import com.tictactoe.domain.model.move.Move
import com.tictactoe.domain.model.move.MovePosition
import com.tictactoe.domain.model.player.Player

class MoveDomainService {
    companion object {
        private val WINNING_COMBINATIONS = listOf(
            listOf(Pair(0, 0), Pair(0, 1), Pair(0, 2)),
            listOf(Pair(1, 0), Pair(1, 1), Pair(1, 2)),
            listOf(Pair(2, 0), Pair(2, 1), Pair(2, 2)),
            listOf(Pair(0, 0), Pair(1, 0), Pair(2, 0)),
            listOf(Pair(0, 1), Pair(1, 1), Pair(2, 1)),
            listOf(Pair(0, 2), Pair(1, 2), Pair(2, 2)),
            listOf(Pair(0, 0), Pair(1, 1), Pair(2, 2)),
            listOf(Pair(0, 2), Pair(1, 1), Pair(2, 0))
        )
        private val initialPlayer: Player = Player.create("X")
        private val secondPlayer: Player = Player.create("O")
    }

    fun validateMove(
        moves: List<Move>,
        x: MovePosition,
        y: MovePosition,
        currentPlayer: Player
    ) {
        if (moves.size >= 9) throw InvalidMoveException("The match is over. No more moves allowed.")
        if (x.value !in 1..3 || y.value !in 1..3) throw InvalidMoveException("Move position out of bounds.")
        if (moves.any { it.x.value == x.value && it.y.value == y.value }) throw InvalidMoveException("The square is already occupied.")
        val isEvenTurn = moves.size % 2 == 0
        val expectedPlayer = if (isEvenTurn) initialPlayer else secondPlayer
        if (currentPlayer != expectedPlayer) {
            throw InvalidTurnException("It's not the turn for player: ${currentPlayer.id}")
        }
    }

    fun checkGameStatus(boardSummary: BoardSummary): Pair<MatchStatus, Player?> {
        val winner = getWinner(boardSummary)
        return when {
            winner != null -> Pair(MatchStatus.ENDED, winner)
            boardSummary.totalMoves == 9 -> Pair(MatchStatus.ENDED, null)
            else -> Pair(MatchStatus.IN_PROGRESS, null)
        }
    }

    private fun getWinner(board: BoardSummary): Player? {
        for (combination in WINNING_COMBINATIONS) {
            val first = board.matrix[combination[0].first][combination[0].second] ?: continue
            if (combination.all { board.matrix[it.first][it.second] == first }) {
                return first
            }
        }
        return null
    }
}
