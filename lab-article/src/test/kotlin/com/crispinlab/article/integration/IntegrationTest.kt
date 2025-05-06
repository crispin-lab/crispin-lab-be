package com.crispinlab.article.integration

import io.restassured.RestAssured
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class IntegrationTest {
    @Autowired
    private lateinit var databaseCleanup: DatabaseCleanup

    @LocalServerPort
    private var port: Int = 0

    @BeforeEach
    fun setup() {
        RestAssured.port
            .takeIf { it == RestAssured.UNDEFINED_PORT }
            ?.let {
                RestAssured.port = port
                databaseCleanup.afterPropertiesSet()
            }
        databaseCleanup.execute()
    }
}
