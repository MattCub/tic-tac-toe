package com.tictactoe.api.dto.createMove

data class CreateMoveResponseDTO(
    val moveId: Long,
    val status: String,
    val winner: String?,
    val currentTurn: String,
    val overall: List<List<String>>
)

