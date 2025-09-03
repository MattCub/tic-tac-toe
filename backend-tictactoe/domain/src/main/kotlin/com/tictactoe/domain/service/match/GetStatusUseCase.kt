package com.tictactoe.domain.service.match

import com.tictactoe.domain.model.match.MatchId
import com.tictactoe.domain.service.match.params.GetStatusResult

interface GetStatusUseCase {
    
    fun execute(matchId: MatchId): GetStatusResult
}