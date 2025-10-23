package com.tlpcraft.cmp.plugin.shared

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

internal val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun Project.applyPlugins(vararg names: String) = names.forEach { name ->
    libs.findPlugin(name).get().get().pluginId.let(pluginManager::apply)
}
