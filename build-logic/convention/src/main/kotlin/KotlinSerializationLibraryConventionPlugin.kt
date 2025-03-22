import com.crispinlab.libs
import com.crispinlab.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KotlinSerializationLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            plugins(
                "kotlinx-serialization"
            )

            dependencies {
                add("implementation", libs.findLibrary("kotlin-serialization-json").get())
                add("implementation", libs.findLibrary("kotlin-reflect").get())
            }
        }
    }
}
