package com.crispinlab.article.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "crispinlab.article")
data class ArticleProperties(
    val enabled: Boolean = true,
    val someProperty: String = "default-value"
)
