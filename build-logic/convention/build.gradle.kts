plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.kotlin.multiplatform.gradlePlugin)
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
}

group = "com.tlpcraft.cmp.plugin.convention"

gradlePlugin {
    plugins {
        register("kotlinMultiplatformLibrary") {
            id = "${project.group}.kotlin.multiplatform.library"
            implementationClass = "${project.group}.KotlinMultiplatformLibrary"
        }
        register("composeMultiplatformLibrary") {
            id = "${project.group}.compose.multiplatform.library"
            implementationClass = "${project.group}.ComposeMultiplatformLibrary"
        }
    }
}
