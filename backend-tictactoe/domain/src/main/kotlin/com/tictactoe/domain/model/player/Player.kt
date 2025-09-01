package com.tictactoe.domain.model.player

import com.tictactoe.domain.exception.MandatoryParameterException

class Player private constructor(
    val id: String
) {
    companion object {
        fun create(id: String): Player {
            if (id.isBlank()) {
                throw MandatoryParameterException("id")
            }
            return Player(id)
        }
    }
}