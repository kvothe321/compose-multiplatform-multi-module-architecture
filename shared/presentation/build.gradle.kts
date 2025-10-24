import com.tlpcraft.cmp.plugin.config.AndroidBuildConfig.ModuleNamespace.PRESENTATION_NAMESPACE

plugins {
    id(libs.plugins.tlpcraft.kotlin.multiplatform.library.get().pluginId)
}

android {
    namespace = PRESENTATION_NAMESPACE
}
