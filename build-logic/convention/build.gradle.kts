plugins {
    `kotlin-dsl`
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    compileOnly(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("jvmLibrary") {
            id = "jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
        register("kotestLibrary") {
            id = "kotest.library"
            implementationClass = "KotestLibraryConventionPlugin"
        }
        register("kopringServiceLibrary") {
            id = "kopring.service.library"
            implementationClass = "KopringServiceLibraryConventionPlugin"
        }
        register("kopringWebLibrary") {
            id = "kopring.web.library"
            implementationClass = "KopringWebLibraryConventionPlugin"
        }
        register("springTestLibrary") {
            id = "spring.test.library"
            implementationClass = "SpringTestLibraryConventionPlugin"
        }
        register("kotlinSnowflake") {
            id = "kotlin.snowflake"
            implementationClass = "SnowflakeLibraryConventionPlugin"
        }
        register("kotlinSerializer") {
            id = "kotlin.serialization"
            implementationClass = "KotlinSerializationLibraryConventionPlugin"
        }
        register("kopringDataJpaLibrary") {
            id = "kopring.data.jpa.library"
            implementationClass = "KopringDataJpaLibraryConventionPlugin"
        }
        register("RestAssured") {
            id = "rest.assured"
            implementationClass = "RestAssuredLibraryConventionPlugin"
        }
    }
}
