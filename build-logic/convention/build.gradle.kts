plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.build.tools)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.compose.compiler.gradle)
    compileOnly(gradleApi())  // Gradle API to access the publishing plugin
}

gradlePlugin {
    plugins {
        register("smartnotesLibrary") {
            id = "daksh.smartnotes.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
    }
}

group = "com.dakshsemwal.smartnotes"