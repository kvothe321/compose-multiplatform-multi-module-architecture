rootProject.name = "CMP-Multimodule-Architecture"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")

    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

include(":composeApp")

include(
    "core",
    "core:domain",
    "core:application"
)

include(
    "shared:data",
    "shared:infrastructure",
    "shared:presentation"
)

include(
    "feature",
    "feature:home",
    "feature:home:core:application",
    "feature:home:core:domain",
    "feature:home:data",
    "feature:home:presentation",
)
