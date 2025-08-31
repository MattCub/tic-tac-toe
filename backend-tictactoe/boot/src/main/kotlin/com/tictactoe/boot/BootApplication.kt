package com.tictactoe.boot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.tictactoe"])
class BootApplication

fun main(args: Array<String>) {
    runApplication<BootApplication>(*args)
}
