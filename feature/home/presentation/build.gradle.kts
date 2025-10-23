import com.tlpcraft.cmp.plugin.config.AndroidBuildConfig.ModuleNamespace.PRESENTATION_NAMESPACE

plugins {
    id(libs.plugins.tlpcraft.kotlin.multiplatform.library.get().pluginId)
    id(libs.plugins.tlpcraft.compose.multiplatform.library.get().pluginId)
}

android {
    namespace = PRESENTATION_NAMESPACE
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":feature:home:core:domain"))
            implementation(project(":feature:home:core:application"))
            implementation(project(":feature:home:data"))
        }
    }
}
