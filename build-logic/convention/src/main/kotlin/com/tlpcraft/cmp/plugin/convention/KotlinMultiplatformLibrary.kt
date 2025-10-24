package com.tlpcraft.cmp.plugin.convention

import com.android.build.api.dsl.LibraryExtension
import com.tlpcraft.cmp.plugin.config.AndroidBuildConfig.COMPILE_SDK
import com.tlpcraft.cmp.plugin.config.AndroidBuildConfig.ModuleNamespace.ROOT_NAMESPACE
import com.tlpcraft.cmp.plugin.shared.PluginDefinitions.ANDROID_LIBRARY
import com.tlpcraft.cmp.plugin.shared.PluginDefinitions.KOTLIN_MULTIPLATFORM
import com.tlpcraft.cmp.plugin.shared.applyPlugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KotlinMultiplatformLibrary : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            println("[BUILD-LOGIC][${this.name}] - Applying Kotlin Multiplatform Library Convention Plugin")
            applyPlugins(
                KOTLIN_MULTIPLATFORM,
                ANDROID_LIBRARY
            )

            configurePlatformTargets()
            configureAndroid()
        }
    }

    private fun Project.configurePlatformTargets() {
        val kotlinMultiplatformExtension = extensions.getByType<KotlinMultiplatformExtension>()
        kotlinMultiplatformExtension.apply {
            configureAndroidTarget()
            configureIosTarget()
            configureDesktopTarget()
            configureWebTarget()
        }
    }

    private fun KotlinMultiplatformExtension.configureAndroidTarget() {
        androidTarget {
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_11)
            }
        }
    }

    private fun KotlinMultiplatformExtension.configureIosTarget() {
        listOf(
            iosArm64(),
            iosSimulatorArm64()
        ).forEach { iosTarget ->
            iosTarget.binaries.framework {
                baseName = "ComposeApp"
                isStatic = true
            }
        }
    }

    private fun KotlinMultiplatformExtension.configureDesktopTarget() {
        jvm()
    }

    private fun KotlinMultiplatformExtension.configureWebTarget() {
        @OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
        wasmJs {
            browser()
            binaries.executable()
        }
    }

    private fun Project.configureAndroid() {
        extensions.getByType<LibraryExtension>().apply {
            namespace = ROOT_NAMESPACE
            compileSdk = COMPILE_SDK
        }
    }
}
