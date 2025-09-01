package com.tictactoe.infrastructure.postgres.move

import com.tictactoe.domain.model.match.Match
import com.tictactoe.domain.model.move.Move
import com.tictactoe.domain.model.move.MoveNumber
import com.tictactoe.domain.model.move.MovePosition
import com.tictactoe.domain.model.player.Player
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(classes = [com.tictactoe.infrastructure.InfrastructureTestApplication::class])
@ActiveProfiles("test")
class PostgresMoveRepositoryImplTest {
    @Autowired
    private lateinit var moveRepository: PostgresMoveRepositoryImpl

    @Autowired
    private lateinit var matchRepository: com.tictactoe.infrastructure.postgres.match.PostgresMatchRepositoryImpl

    private val MATCH: Match = Match.Builder().build()

    private val PLAYER: Player = Player.create("X")

    private val X: MovePosition = MovePosition.create(2)

    private val Y: MovePosition = MovePosition.create(3)

    private val MOVENUMBER: MoveNumber = MoveNumber.create(1)

    @Test
    fun `should create match and generate id`() {
        val persistedMatch = matchRepository.create(MATCH)
        val move = Move.Builder()
            .match(persistedMatch)
            .player(PLAYER)
            .x(X)
            .y(Y)
            .moveNumber(MOVENUMBER)
            .build()

        val savedMove = moveRepository.create(move)
        
        assertNotNull(savedMove.id)
        assertEquals(persistedMatch.id?.value, savedMove.match.id?.value)
        assertEquals(PLAYER.id, savedMove.player.id)
        assertEquals(X.value, savedMove.x.value)
        assertEquals(Y.value, savedMove.y.value)
        assertEquals(MOVENUMBER.value, savedMove.moveNumber.value)
        assertNotNull(savedMove.createdAt)

        moveRepository.deleteById(savedMove.id!!.value)
        matchRepository.deleteById(persistedMatch.id!!.value)
    }
}