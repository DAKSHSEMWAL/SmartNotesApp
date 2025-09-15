package com.dakshsemwal.smartnotes

import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

object Config {
    const val compileSdkVersion = 35
    const val targetSdkVersion = 35
    const val minSdkVersion = 28
    val sourceCompatibility = JavaVersion.VERSION_21
    val targetCompatibility = JavaVersion.VERSION_21
    val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    val jvmTarget = JavaVersion.VERSION_21.toString()
    val JVM_TARGET = JvmTarget.JVM_21
    val libraryId = "com.dakshsemwal.smartnotes"
}