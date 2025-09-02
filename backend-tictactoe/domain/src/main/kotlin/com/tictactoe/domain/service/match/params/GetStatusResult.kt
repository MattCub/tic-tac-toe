package com.tictactoe.domain.service.match.params

import com.tictactoe.domain.exception.MandatoryParameterException
import com.tictactoe.domain.model.match.MatchStatus
import com.tictactoe.domain.model.move.BoardSummary
import com.tictactoe.domain.model.player.Player

class GetStatusResult private constructor(
    val status: MatchStatus,
    val currentTurn: Player,
    val winner: Player? = null,
    val overall: BoardSummary
) {
    class Builder {
        private var status: MatchStatus? = null
        private var currentTurn: Player? = null
        private var winner: Player? = null
        private var overall: BoardSummary? = null

        fun status(status: MatchStatus) = apply { this.status = status }
        fun currentTurn(currentTurn: Player) = apply { this.currentTurn = currentTurn }
        fun winner(winner: Player?) = apply { this.winner = winner }
        fun overall(overall: BoardSummary) = apply { this.overall = overall }

        fun build(): GetStatusResult {
            val status = this.status ?: throw MandatoryParameterException("status is required")
            val currentTurn = this.currentTurn ?: throw MandatoryParameterException("currentTurn is required")
            val overall = this.overall ?: throw MandatoryParameterException("overall is required")
            return GetStatusResult(status, currentTurn, winner, overall)
        }
    }
}