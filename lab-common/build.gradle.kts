plugins {
    alias(libs.plugins.jvm.library)
    alias(libs.plugins.kotlin.spring.web)
    alias(libs.plugins.kotlin.snowflake)
}
tasks.named("bootJar") {
    enabled = false
}

tasks.named("jar") {
    enabled = true
}
