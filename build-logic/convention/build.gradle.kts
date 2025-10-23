plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.kotlin.multiplatform.gradlePlugin)
    compileOnly(libs.android.gradlePlugin)
}

group = "com.tlpcraft.cmp.plugin.convention"

gradlePlugin {
    plugins {
        register("kotlinMultiplatformLibrary") {
            id = "${project.group}.kotlin.multiplatform.library"
            implementationClass = "${project.group}.KotlinMultiplatformLibrary"
        }
    }
}
