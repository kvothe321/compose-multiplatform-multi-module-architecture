import com.tlpcraft.cmp.plugin.config.AndroidBuildConfig.ModuleNamespace.APPLICATION_NAMESPACE

plugins {
    id(libs.plugins.tlpcraft.kotlin.multiplatform.library.get().pluginId)
}

android {
    namespace = APPLICATION_NAMESPACE
}
