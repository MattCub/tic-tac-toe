package com.tictactoe.boot.configuration

import com.tictactoe.domain.service.move.MoveDomainService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Configuration {

    @Bean
    fun moveDomainService(): MoveDomainService {
        return MoveDomainService()
    }
}