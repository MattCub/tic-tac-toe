package com.tictactoe.domain.model.move

import com.tictactoe.domain.exception.InvalidParameterException

class MovePosition private constructor(val value: Int) {
    companion object {
        fun create(value: Int): MovePosition {
            if (value < 1 || value > 3) {
                throw InvalidParameterException("Move position must be between 0 and 2")
            }
            return MovePosition(value)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is MovePosition) return false
        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}