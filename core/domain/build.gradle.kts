import com.tlpcraft.cmp.plugin.config.AndroidBuildConfig.ModuleNamespace.DOMAIN_NAMESPACE

plugins {
    id(libs.plugins.tlpcraft.kotlin.multiplatform.library.get().pluginId)
}

android {
    namespace = DOMAIN_NAMESPACE
}
