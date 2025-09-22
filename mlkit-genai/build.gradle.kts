import com.dakshsemwal.smartnotes.Config

plugins {
    alias(libs.plugins.daksh.smartnotes.library)
}

android {
    namespace = "${Config.libraryId}.mlkit_genai"
    defaultConfig {
        minSdk = Config.minSdkVersion
    }
}

dependencies {
    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // Coroutines
    implementation(libs.kotlinx.coroutines)

    // ML Kit GenAI Summarization
    implementation(libs.genai.summarization)

    // For ListenableFuture<T>.await()
    implementation(libs.kotlinx.coroutines.guava)
}
