package com.crispinlab.common.config

import com.crispinlab.Snowflake
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GenerateIdConfig {
    @Bean
    fun snowflake(): Snowflake = Snowflake.create()
}
