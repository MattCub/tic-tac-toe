package com.tictactoe.infrastructure.postgres.move

import com.tictactoe.domain.model.match.Match
import com.tictactoe.domain.model.move.Move
import com.tictactoe.domain.model.move.MoveNumber
import com.tictactoe.domain.model.move.MovePosition
import com.tictactoe.domain.model.player.Player
import com.tictactoe.infrastructure.postgres.match.PostgresMatchRepositoryImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional

@SpringBootTest(classes = [com.tictactoe.infrastructure.InfrastructureTestApplication::class])
@ActiveProfiles("test")
class PostgresMoveRepositoryImplIntegrationTest {
    @Autowired
    private lateinit var moveRepository: PostgresMoveRepositoryImpl

    @Autowired
    private lateinit var matchRepository: PostgresMatchRepositoryImpl

    private val MATCH: Match = Match.Builder().build()

    private val PLAYER_X: Player = Player.create("X")

    private val PLAYER_O: Player = Player.create("O")

    private val X_1: MovePosition = MovePosition.create(2)

    private val X_2: MovePosition = MovePosition.create(1)

    private val Y_1: MovePosition = MovePosition.create(3)

    private val Y_2: MovePosition = MovePosition.create(1)

    private val MOVENUMBER_1: MoveNumber = MoveNumber.create(1)

    private val MOVENUMBER_2: MoveNumber = MoveNumber.create(1)

    @Test
    fun `should create match and generate id`() {
        val persistedMatch: Match = matchRepository.create(MATCH)
        val move1: Move = createMove1(persistedMatch)

        val savedMove = moveRepository.create(move1)

        assertNotNull(savedMove.id)
        assertEquals(persistedMatch.id?.value, savedMove.match.id?.value)
        assertEquals(PLAYER_X.id, savedMove.player.id)
        assertEquals(X_1.value, savedMove.x.value)
        assertEquals(Y_1.value, savedMove.y.value)
        assertEquals(MOVENUMBER_1.value, savedMove.moveNumber.value)
        assertNotNull(savedMove.createdAt)

        moveRepository.deleteById(savedMove.id!!.value)
        matchRepository.deleteById(persistedMatch.id!!.value)
    }

    @Test
    @Transactional
    fun `should create two moves and find all by matchId`() {
        val persistedMatch: Match = matchRepository.create(MATCH)
        val move1: Move = createMove1(persistedMatch)
        val move2: Move = createMove2(persistedMatch)

        val savedMove1 = moveRepository.create(move1)
        val savedMove2 = moveRepository.create(move2)

        val moves = moveRepository.findByMatchId(persistedMatch.id!!.value)

        assertEquals(2, moves.size)

        moveRepository.deleteById(savedMove1.id!!.value)
        moveRepository.deleteById(savedMove2.id!!.value)
        matchRepository.deleteById(persistedMatch.id!!.value)
    }

    private fun createMove1(match: Match): Move {
        return Move.Builder()
            .match(match)
            .player(PLAYER_X)
            .x(X_1)
            .y(Y_1)
            .moveNumber(MOVENUMBER_1)
            .build()
    }

    private fun createMove2(match: Match): Move {
        return Move.Builder()
            .match(match)
            .player(PLAYER_O)
            .x(X_2)
            .y(Y_2)
            .moveNumber(MOVENUMBER_2)
            .build()
    }
}