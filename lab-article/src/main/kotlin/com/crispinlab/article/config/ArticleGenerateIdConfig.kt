package com.crispinlab.article.config

import com.crispinlab.Snowflake
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ArticleGenerateIdConfig {
    @Bean
    @ConditionalOnMissingBean(Snowflake::class)
    fun snowflake(): Snowflake = Snowflake.create()
}
