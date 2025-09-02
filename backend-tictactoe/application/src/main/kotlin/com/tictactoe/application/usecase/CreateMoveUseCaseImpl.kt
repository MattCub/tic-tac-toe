package com.tictactoe.application.usecase

import com.tictactoe.domain.exception.NotFoundException
import com.tictactoe.domain.model.match.Match
import com.tictactoe.domain.model.match.MatchStatus
import com.tictactoe.domain.model.move.BoardSummary
import com.tictactoe.domain.model.move.Move
import com.tictactoe.domain.model.move.MoveNumber
import com.tictactoe.domain.repositories.match.MatchRepository
import com.tictactoe.domain.repositories.move.MoveRepository
import com.tictactoe.domain.service.move.CreateMoveUseCase
import com.tictactoe.domain.service.move.MoveDomainService
import com.tictactoe.domain.service.move.params.CreateMoveParams
import com.tictactoe.domain.service.move.params.CreateMoveResult
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CreateMoveUseCaseImpl(
    private val matchRepository: MatchRepository,
    private val moveRepository: MoveRepository,
    private val moveDomainService: MoveDomainService
) : CreateMoveUseCase {

    val logger = LoggerFactory.getLogger(CreateMoveUseCaseImpl::class.java)

    override fun execute(params: CreateMoveParams): CreateMoveResult {
        try {
            logger.info(
                "Creating move for matchId={}, player={}, x={}, y={}",
                params.matchId,
                params.player,
                params.x,
                params.y
            )
            val match: Match = matchRepository.findById(params.matchId.value)
                ?: throw NotFoundException("match", params.matchId.value.toString())
            val moves: List<Move> = moveRepository.findByMatchId(params.matchId.value)
            moveDomainService.validateMove(moves, params.x, params.y, params.player, match.status)
            val newMove = Move.Builder()
                .match(match)
                .player(params.player)
                .x(params.x)
                .y(params.y)
                .moveNumber(MoveNumber.create(moves.size + 1))
                .build()
            val savedMove: Move = moveRepository.create(newMove)
            logger.info(
                "Move {} created for match {}",
                savedMove.id,
                params.matchId
            )
            val updatedMoves: List<Move> = moves + savedMove
            val boardSummary = BoardSummary.fromMoves(updatedMoves)
            val (status, winner) = moveDomainService.checkGameStatus(boardSummary)
            if (status != match.status) {
                val updateMatch: Match = Match.Builder()
                    .id(match.id!!)
                    .status(status)
                    .winner(if (status == MatchStatus.ENDED) winner else null)
                    .createdAt(match.createdAt)
                    .build()
                matchRepository.update(updateMatch)
                logger.info("Match {} status updated to {}", match.id, status)
            }
            return CreateMoveResult.Builder()
                .moveId(savedMove.id!!)
                .status(status)
                .winner(winner)
                .boardSummary(updatedMoves)
                .build()
        } catch (ex: Exception) {
            logger.error(
                "Error creating move for matchId {} for player {} and coordinates {},{}: {}",
                params.matchId,
                params.player,
                params.x,
                params.y,
                ex.message,
                ex
            )
            throw ex
        }
    }
}