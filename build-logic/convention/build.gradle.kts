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
        register("kopringLibrary") {
            id = "kopring.library"
            implementationClass = "KopringLibraryConventionPlugin"
        }
        register("springTestLibrary") {
            id = "spring.test.library"
            implementationClass = "SpringTestLibraryConventionPlugin"
        }
    }
}
