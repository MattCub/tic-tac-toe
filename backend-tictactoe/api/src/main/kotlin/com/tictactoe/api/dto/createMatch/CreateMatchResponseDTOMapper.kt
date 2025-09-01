package com.tictactoe.api.dto.createMatch

import com.tictactoe.domain.model.match.MatchId

object CreateMatchResponseDTOMapper {

    fun toDTO(matchId: MatchId): CreateMatchResponseDTO {
        return CreateMatchResponseDTO(
            matchId = matchId.value
        )
    }


}