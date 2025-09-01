package com.tictactoe.domain.model.match

class MatchId private constructor(val value: Long){
    companion object {
        fun create(value: Long): MatchId {
            return MatchId(value)
        }
    }
}