package com.tictactoe.application.usecase

import com.tictactoe.domain.exception.NotFoundException
import com.tictactoe.domain.model.match.MatchId
import com.tictactoe.domain.model.move.BoardSummary
import com.tictactoe.domain.repositories.match.MatchRepository
import com.tictactoe.domain.repositories.move.MoveRepository
import com.tictactoe.domain.service.match.GetStatusUseCase
import com.tictactoe.domain.service.match.params.GetStatusResult
import org.springframework.stereotype.Service

@Service
class GetStatusUseCaseImpl(val matchRepository: MatchRepository, val moveRepository: MoveRepository) :
    GetStatusUseCase {
    override fun execute(matchId: MatchId): GetStatusResult {
        val match =
            matchRepository.findById(matchId.value) ?: throw NotFoundException("Match", matchId.value.toString())
        val moves = moveRepository.findByMatchId(matchId.value)
        val boardSummary: BoardSummary = BoardSummary.fromMoves(moves)
        return GetStatusResult.Builder()
            .status(match.status)
            .currentTurn(boardSummary.getCurrentTurn())
            .winner(match.winner)
            .overall(boardSummary)
            .build()
    }


}