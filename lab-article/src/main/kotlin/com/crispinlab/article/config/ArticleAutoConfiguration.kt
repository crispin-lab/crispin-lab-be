package com.crispinlab.article.config

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@ComponentScan("com.crispinlab.article")
@EnableConfigurationProperties(ArticleProperties::class)
@ConditionalOnProperty(
    prefix = "crispinlab.article",
    name = ["enabled"],
    havingValue = "true",
    matchIfMissing = true
)
@EnableJpaRepositories("com.crispinlab.article.adapter.output.persistence.repository")
class ArticleAutoConfiguration
