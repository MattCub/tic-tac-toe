package com.tictactoe.api.dto.createMove

data class CreateMoveRequestDTO(
    val playerId: String,
    val square: SquareDTO
) {
    data class SquareDTO(val x: Int, val y: Int)
}
