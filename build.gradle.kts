// Top-level build file where you can add configuration options common to all sub-projects/modules.
allprojects {
    repositories {
        google()  // Ensure this is included
        mavenCentral()
    }
}



plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    id("com.google.devtools.ksp") version "1.9.0-1.0.12" apply false
}