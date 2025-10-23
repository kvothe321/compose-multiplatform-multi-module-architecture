import com.tlpcraft.cmp.plugin.config.AndroidBuildConfig.ROOT_NAMESPACE

plugins {
    id(libs.plugins.tlpcraft.kotlin.multiplatform.library.get().pluginId)
}

android {
    namespace = "$ROOT_NAMESPACE.presentation"
}
