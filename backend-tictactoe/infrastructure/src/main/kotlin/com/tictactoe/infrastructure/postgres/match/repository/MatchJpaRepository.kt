package com.tictactoe.infrastructure.postgres.match.repository

import com.tictactoe.infrastructure.postgres.match.entity.MatchEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MatchJpaRepository : JpaRepository<MatchEntity, Long>

