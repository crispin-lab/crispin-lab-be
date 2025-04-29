package com.crispinlab.board.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@ComponentScan("com.crispinlab.board")
@EnableJpaRepositories("com.crispinlab.board.adapter.output.persistence.repository")
class BoardAutoConfiguration
