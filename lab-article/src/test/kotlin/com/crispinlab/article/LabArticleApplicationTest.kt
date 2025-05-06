package com.crispinlab.article

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
    properties = ["crispinlab.article.enabled=false"]
)
class LabArticleApplicationTest {
    @Test
    fun contextLoads() {
    }
}
