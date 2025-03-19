import com.crispinlab.libs
import com.crispinlab.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KopringServiceLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            plugins(
                "org.springframework.boot",
                "io.spring.dependency-management",
                "org.jetbrains.kotlin.plugin.spring",
                "spring.test.library"
            )
            tasks.named("bootJar") {
                enabled = false
            }

            tasks.named("jar") {
                enabled = true
            }
            dependencies {
                add("implementation", libs.findLibrary("spring.boot.starter").get())
            }
        }
    }
}
