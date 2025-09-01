package com.tictactoe.domain.model

import com.tictactoe.domain.model.match.Match
import com.tictactoe.domain.model.match.MatchId
import com.tictactoe.domain.model.match.MatchStatus
import com.tictactoe.domain.model.player.Player
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import kotlin.test.assertEquals
import kotlin.test.assertNull

class MatchTest {

    @Test
    fun `should build match with default values`() {
        val match = Match.Builder().build()
        assertNull(match.id)
        assertEquals(MatchStatus.CREATED, match.status)
        assertNull(match.winner)
        assertNotNull(match.createdAt)
    }

    @Test
    fun `should build match with custom values`() {
        val now = LocalDateTime.of(2024, 6, 1, 12, 0)
        val player = Player.create("O")
        val match = Match.Builder()
            .id(MatchId.create(10L))
            .winner(player)
            .createdAt(now)
            .build()

        assertEquals(10L, match.id?.value)
        assertEquals(MatchStatus.CREATED, match.status)
        assertEquals(player, match.winner)
        assertEquals(now, match.createdAt)
    }
}