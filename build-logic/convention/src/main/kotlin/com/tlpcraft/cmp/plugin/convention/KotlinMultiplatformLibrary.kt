package com.tlpcraft.cmp.plugin.convention

import com.tlpcraft.cmp.plugin.shared.PluginDefinitions.ANDROID_LIBRARY
import com.tlpcraft.cmp.plugin.shared.PluginDefinitions.KOTLIN_MULTIPLATFORM
import com.tlpcraft.cmp.plugin.shared.applyPlugins
import org.gradle.api.Plugin
import org.gradle.api.Project

class KotlinMultiplatformLibrary : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            println("[BUILD-LOGIC] - Applying Kotlin Multiplatform Library Convention Plugin")
            applyPlugins(
                KOTLIN_MULTIPLATFORM,
                ANDROID_LIBRARY
            )
        }
    }
}
