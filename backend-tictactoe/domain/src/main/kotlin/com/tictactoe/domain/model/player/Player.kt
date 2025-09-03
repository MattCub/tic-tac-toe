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
            return Player(id.uppercase())
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is Player) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}