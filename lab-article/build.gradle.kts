plugins {
    alias(libs.plugins.jvm.library)
    alias(libs.plugins.kotlin.spring.web)
    alias(libs.plugins.kotlin.snowflake)
    alias(libs.plugins.kotlin.spring.data.jpa)
}
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
}
