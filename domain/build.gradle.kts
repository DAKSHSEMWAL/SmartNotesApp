import com.dakshsemwal.smartnotes.Config

plugins {
    alias(libs.plugins.daksh.smartnotes.library)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "${Config.libraryId}.domain"
}
dependencies {
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(libs.kotlinx.coroutines)

    implementation(projects.data)

}