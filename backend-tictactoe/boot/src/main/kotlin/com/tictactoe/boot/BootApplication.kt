package com.tictactoe.boot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication(scanBasePackages = ["com.tictactoe"])
@EnableJpaRepositories(basePackages = ["com.tictactoe.infrastructure.postgres.match.repository", "com.tictactoe.infrastructure.postgres.move.repository"])
@EntityScan(basePackages = ["com.tictactoe.infrastructure.postgres.match.entity", "com.tictactoe.infrastructure.postgres.move.entity"])
class BootApplication

fun main(args: Array<String>) {
    runApplication<BootApplication>(*args)
}
