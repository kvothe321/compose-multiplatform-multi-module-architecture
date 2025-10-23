import com.tlpcraft.cmp.plugin.config.AndroidBuildConfig.ModuleNamespace.DATA_NAMESPACE

plugins {
    id(libs.plugins.tlpcraft.kotlin.multiplatform.library.get().pluginId)
}

android {
    namespace = DATA_NAMESPACE
}
