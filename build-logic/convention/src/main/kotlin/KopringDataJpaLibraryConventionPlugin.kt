import com.crispinlab.libs
import com.crispinlab.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KopringDataJpaLibraryConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            plugins(
                "kotlin-jpa"
            )
            dependencies {
                add("implementation", libs.findLibrary("spring.boot.starter.data.jpa").get())
                add("runtimeOnly", libs.findLibrary("h2.database").get())
            }
        }
    }
}
