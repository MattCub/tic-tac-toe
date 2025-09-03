package com.tictactoe.domain.model.match

class MatchId private constructor(val value: Long) {
    companion object {
        fun create(value: Long): MatchId {
            return MatchId(value)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is MatchId) return false
        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}
