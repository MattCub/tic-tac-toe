package com.tictactoe.api.dto.getStatus

import com.tictactoe.domain.service.match.params.GetStatusResult

object GetStatusResponseDTOMapper {

    fun toDTO(getStatusResult: GetStatusResult): GetStatusResponseDTO {
        return GetStatusResponseDTO(
            status = getStatusResult.status.name,
            currentTurn = getStatusResult.currentTurn.id,
            overall = getStatusResult.overall.matrix.map { rows -> rows.map { it?.id ?: "-" } }
        )
    }
}