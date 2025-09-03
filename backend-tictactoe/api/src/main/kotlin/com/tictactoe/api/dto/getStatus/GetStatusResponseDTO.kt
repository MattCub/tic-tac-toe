package com.tictactoe.api.dto.getStatus

data class GetStatusResponseDTO(
    val status: String,
    val currentTurn: String,
    val winner: String?,
    val overall: List<List<String>>
)