package com.crispinlab.article.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@ComponentScan("com.crispinlab.article")
@EnableJpaRepositories("com.crispinlab.article.adapter.output.persistence.repository")
class ArticleAutoConfiguration
