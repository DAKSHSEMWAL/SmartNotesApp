import com.dakshsemwal.smartnotes.Config

plugins {
    alias(libs.plugins.daksh.smartnotes.library)
}

android {
    namespace =  "${Config.libraryId}.data"
    defaultConfig {
        minSdk = Config.minSdkVersion
    }
}

dependencies {
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(libs.kotlinx.coroutines)
    implementation(projects.mlkitGenai)
}