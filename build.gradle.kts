// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) version "8.5.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
    id("com.google.devtools.ksp") version "1.9.0-1.0.13" apply false
    alias(libs.plugins.android.library) apply false
    id("org.jetbrains.kotlin.jvm") version "1.9.0" apply false
    alias(libs.plugins.android.dynamic.feature) apply false
}

buildscript {
    repositories {
        google()
    }
    dependencies {
        val navVersion = "2.7.7"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navVersion")
    }
}