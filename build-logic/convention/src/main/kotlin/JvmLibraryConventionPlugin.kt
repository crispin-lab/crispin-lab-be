import com.crispinlab.configureKotlinJvm
import com.crispinlab.linterGitHooksInstall
import com.crispinlab.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project

class JvmLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            plugins(
                "java-library",
                "org.jetbrains.kotlin.jvm",
                "org.jmailen.kotlinter",
                "kotest.library"
            )
            configureKotlinJvm()
            linterGitHooksInstall()
        }
    }
}
