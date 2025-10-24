import com.tlpcraft.cmp.plugin.config.AndroidBuildConfig.ModuleNamespace.INFRASTRUCTURE_NAMESPACE

plugins {
    id(libs.plugins.tlpcraft.kotlin.multiplatform.library.get().pluginId)
}

android {
    namespace = INFRASTRUCTURE_NAMESPACE
}
