package com.tictactoe.api.dto.createMove

import com.tictactoe.domain.service.move.params.CreateMoveResult

object CreateMoveResponseDTOMapper {

    fun toDTO(
        createMoveResult: CreateMoveResult
    ): CreateMoveResponseDTO {
        return CreateMoveResponseDTO(
            moveId = createMoveResult.moveId.value,
            status = createMoveResult.status.name,
            winner = createMoveResult.winner?.id,
            currentTurn = createMoveResult.boardSummary.getCurrentTurn().id,
            overall = createMoveResult.boardSummary.matrix.map { rows -> rows.map { it?.id ?: "-" } }
        )
    }
}