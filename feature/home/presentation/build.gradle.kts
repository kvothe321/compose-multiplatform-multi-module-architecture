import com.tlpcraft.cmp.plugin.config.AndroidBuildConfig.ModuleNamespace.PRESENTATION_NAMESPACE

plugins {
    id(libs.plugins.tlpcraft.kotlin.multiplatform.library.get().pluginId)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
}

android {
    namespace = PRESENTATION_NAMESPACE
}

kotlin {
    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(project(":feature:home:core:domain"))
            implementation(project(":feature:home:core:application"))
            implementation(project(":feature:home:data"))

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
        }
    }
}
