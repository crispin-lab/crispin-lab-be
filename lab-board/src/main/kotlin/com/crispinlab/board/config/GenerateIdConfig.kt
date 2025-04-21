package com.crispinlab.board.config

import com.crispinlab.Snowflake
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GenerateIdConfig {
    @Bean
    fun snowflake(): Snowflake = Snowflake.create()
}
