rootProject.name = "crispin-lab-be"

pluginManagement {
    includeBuild("build-logic")
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }

    dependencyResolutionManagement {
        repositories {
            mavenCentral()
        }
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include("demo")
include(
    ":lab-article",
    ":lab-board"
)
