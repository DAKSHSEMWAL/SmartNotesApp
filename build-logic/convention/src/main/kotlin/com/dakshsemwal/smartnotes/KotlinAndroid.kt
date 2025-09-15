package com.dakshsemwal.smartnotes

import com.android.build.api.dsl.CommonExtension
import com.dakshsemwal.smartnotes.Config.JVM_TARGET
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = Config.compileSdkVersion

        defaultConfig {
            minSdk = Config.minSdkVersion
            buildFeatures.buildConfig = true
            testInstrumentationRunner = Config.testInstrumentationRunner
            vectorDrawables.useSupportLibrary = true
        }

        buildFeatures {
            buildConfig = true
            compose = true
        }

        compileOptions {
            sourceCompatibility = Config.sourceCompatibility
            targetCompatibility = Config.targetCompatibility
        }

        plugins.withId("org.jetbrains.kotlin.android") {
            extensions.configure(KotlinAndroidProjectExtension::class.java) {
                compilerOptions {
                    jvmTarget.set(JVM_TARGET)
                }
            }
        }

        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }

        lint {
            baseline = file("${rootDir.path}/lint-baseline.xml")
            sarifReport = true
            htmlReport = true
            textReport = false
            xmlReport = false
            checkDependencies = true
        }

        testOptions {
            unitTests.apply {
                isReturnDefaultValues = true
                isIncludeAndroidResources = true
                isReturnDefaultValues = true
            }
        }
    }
}