package com.tlpcraft.cmp.plugin.config

object AndroidBuildConfig {
    const val COMPILE_SDK = 36
    const val MIN_SDK = 24
    const val TARGET_SDK = 36

    object ModuleNamespace {
        const val ROOT_NAMESPACE = "com.tlpcraft.cmp"
        const val DOMAIN_NAMESPACE = "$ROOT_NAMESPACE.domain"
        const val APPLICATION_NAMESPACE = "$ROOT_NAMESPACE.application"
        const val DATA_NAMESPACE = "$ROOT_NAMESPACE.data"
        const val PRESENTATION_NAMESPACE = "$ROOT_NAMESPACE.presentation"
        const val INFRASTRUCTURE_NAMESPACE = "$ROOT_NAMESPACE.infrastructure"
    }
}
