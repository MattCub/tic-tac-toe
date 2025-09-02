package com.tictactoe.domain.model.move

import com.tictactoe.domain.exception.MandatoryParameterException
import com.tictactoe.domain.model.match.Match
import com.tictactoe.domain.model.match.MatchId
import com.tictactoe.domain.model.player.Player
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class MoveTest {

    private val MATCH: Match = Match.Builder().id(MatchId.create(1L)).build()

    @Test
    fun `builder creates Move correctly with all mandatory parameters`() {
        val move = Move.Builder()
            .match(MATCH)
            .player(Player.create("O"))
            .x(MovePosition.create(1))
            .y(MovePosition.create(2))
            .moveNumber(MoveNumber.create(3))
            .createdAt(LocalDateTime.now())
            .build()
        assertNotNull(move)
        assertEquals(1L, MATCH.id?.value)
        assertEquals(1, move.x.value)
        assertEquals(2, move.y.value)
        assertEquals(3, move.moveNumber.value)
    }

    @Test
    fun `builder throws exception if matchId is missing`() {
        val builder = Move.Builder()
            .player(Player.create("O"))
            .x(MovePosition.create(1))
            .y(MovePosition.create(2))
            .moveNumber(MoveNumber.create(3))
        assertThrows(MandatoryParameterException::class.java) {
            builder.build()
        }
    }

    @Test
    fun `builder throws exception if player is missing`() {
        val builder = Move.Builder()
            .match(MATCH)
            .x(MovePosition.create(1))
            .y(MovePosition.create(2))
            .moveNumber(MoveNumber.create(3))
        assertThrows(MandatoryParameterException::class.java) {
            builder.build()
        }
    }

    @Test
    fun `builder throws exception if x is missing`() {
        val builder = Move.Builder()
            .match(MATCH)
            .player(Player.create("O"))
            .y(MovePosition.create(2))
            .moveNumber(MoveNumber.create(3))
        assertThrows(MandatoryParameterException::class.java) {
            builder.build()
        }
    }

    @Test
    fun `builder throws exception if y is missing`() {
        val builder = Move.Builder()
            .match(MATCH)
            .player(Player.create("O"))
            .x(MovePosition.create(1))
            .moveNumber(MoveNumber.create(3))
        assertThrows(MandatoryParameterException::class.java) {
            builder.build()
        }
    }

    @Test
    fun `builder throws exception if moveNumber is missing`() {
        val builder = Move.Builder()
            .match(MATCH)
            .player(Player.create("O"))
            .x(MovePosition.create(1))
            .y(MovePosition.create(2))
        assertThrows(MandatoryParameterException::class.java) {
            builder.build()
        }
    }
}