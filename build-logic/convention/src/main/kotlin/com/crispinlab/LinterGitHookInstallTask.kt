package com.crispinlab

import java.io.File
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

internal sealed class LinterGitHookInstallTask(
    private val hookName: String,
    private val gradleTask: String
) : DefaultTask() {
    @TaskAction
    fun installHook() {
        val hookFile: File = project.rootProject.file(".git/hooks/$hookName")
        hookFile.parentFile.mkdirs()
        hookFile.writeText(createHookScript())

        runCatching { hookFile.setExecutable(true) }
            .onFailure { logger.warn("Could not set executable permission for $hookFile") }
    }

    private fun createHookScript(): String =
        """
        #!/bin/sh
        set -e
        ./gradlew $gradleTask
        """.trimIndent()
}

internal abstract class InstallPreCommitHookTask :
    LinterGitHookInstallTask(
        "pre-commit",
        "lintKotlin"
    )

internal abstract class InstallPrePushHookTask :
    LinterGitHookInstallTask(
        "pre-push",
        "formatKotlin"
    )
