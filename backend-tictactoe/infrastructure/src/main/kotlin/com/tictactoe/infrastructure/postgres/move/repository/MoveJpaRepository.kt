package com.tictactoe.infrastructure.postgres.move.repository

import com.tictactoe.infrastructure.postgres.move.entity.MoveEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MoveJpaRepository : JpaRepository<MoveEntity, Long>