package com.tictactoe.infrastructure.postgres.match

import com.tictactoe.domain.model.match.Match
import com.tictactoe.domain.model.match.MatchStatus
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(classes = [com.tictactoe.infrastructure.InfrastructureTestApplication::class])
@ActiveProfiles("test")
class PostgresMatchRepositoryImplIntegrationTest @Autowired constructor(
    private val matchRepository: PostgresMatchRepositoryImpl
) {
    @Test
    fun `should create match and generate id`() {
        val match = Match.Builder().build()

        val savedMatch = matchRepository.create(match)

        assertNotNull(savedMatch.id)
        assertEquals(MatchStatus.CREATED, savedMatch.status)
        assertNull(savedMatch.winner)
        assertNotNull(savedMatch.createdAt)

        matchRepository.deleteById(savedMatch.id!!.value)
    }
}
