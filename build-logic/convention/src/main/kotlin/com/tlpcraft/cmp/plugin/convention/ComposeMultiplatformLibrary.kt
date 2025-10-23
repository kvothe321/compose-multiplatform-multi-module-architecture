package com.tlpcraft.cmp.plugin.convention

import com.tlpcraft.cmp.plugin.shared.PluginDefinitions.COMPOSE_COMPILER
import com.tlpcraft.cmp.plugin.shared.PluginDefinitions.COMPOSE_HOT_RELOAD
import com.tlpcraft.cmp.plugin.shared.PluginDefinitions.COMPOSE_MULTIPLATFORM
import com.tlpcraft.cmp.plugin.shared.applyPlugins
import com.tlpcraft.cmp.plugin.shared.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposePlugin
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class ComposeMultiplatformLibrary : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            println("[BUILD-LOGIC] - Applying Compose Multiplatform Library Convention Plugin")
            applyPlugins(
                COMPOSE_MULTIPLATFORM,
                COMPOSE_COMPILER,
                COMPOSE_HOT_RELOAD
            )

            declareDefaultDependencies()
        }
    }

    private fun Project.declareDefaultDependencies() {
        extensions.getByType<KotlinMultiplatformExtension>().apply {
            val compose = extensions.getByType<ComposePlugin.Dependencies>()

            sourceSets.apply {
                androidMain.dependencies {
                    implementation(compose.preview)
                }

                val viewModelCompose = libs
                    .findLibrary("androidx-lifecycle-viewmodelCompose")
                    .orElseThrow { IllegalStateException("Library alias 'androidx-lifecycle-viewmodel-compose' not found in libs.toml") }

                val runtimeCompose = libs
                    .findLibrary("androidx-lifecycle-runtimeCompose")
                    .orElseThrow { IllegalStateException("Library alias 'androidx-lifecycle-runtime-compose' not found in libs.toml") }

                commonMain.dependencies {
                    implementation(compose.runtime)
                    implementation(compose.foundation)
                    implementation(compose.material3)
                    implementation(compose.ui)
                    implementation(compose.components.resources)
                    implementation(compose.components.uiToolingPreview)
                    implementation(viewModelCompose)
                    implementation(runtimeCompose)
                }
            }
        }
    }
}
