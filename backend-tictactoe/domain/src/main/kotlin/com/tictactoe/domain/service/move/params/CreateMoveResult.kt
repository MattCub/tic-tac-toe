package com.tictactoe.domain.service.move.params

import com.tictactoe.domain.exception.MandatoryParameterException
import com.tictactoe.domain.model.match.MatchStatus
import com.tictactoe.domain.model.move.BoardSummary
import com.tictactoe.domain.model.move.Move
import com.tictactoe.domain.model.move.MoveId
import com.tictactoe.domain.model.player.Player

class CreateMoveResult private constructor(
    val moveId: MoveId,
    val status: MatchStatus,
    val winner: Player?,
    val boardSummary: BoardSummary
) {
    class Builder {
        private var moveId: MoveId? = null
        private var status: MatchStatus? = null
        private var winner: Player? = null
        private var boardSummary: BoardSummary? = null

        fun moveId(moveId: MoveId) = apply { this.moveId = moveId }
        fun status(status: MatchStatus) = apply { this.status = status }
        fun winner(winner: Player?) = apply { this.winner = winner }
        fun boardSummary(moves: List<Move>) = apply { this.boardSummary = BoardSummary.fromMoves(moves) }

        fun build(): CreateMoveResult {
            val moveId = this.moveId ?: throw MandatoryParameterException("moveId")
            val status = this.status ?: throw MandatoryParameterException("status")
            val boardSummary = boardSummary ?: throw MandatoryParameterException("boardSummary")
            return CreateMoveResult(moveId, status, winner, boardSummary)
        }
    }
}
