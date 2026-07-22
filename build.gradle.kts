// ROOT BUILD FILE - Optimized for PlugPact Build Engine
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // Core Android & Kotlin build tools
        classpath("com.android.tools.build:gradle:8.2.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
    }
}

// We removed the "allprojects" block because repositories are now 
// managed in settings.gradle.kts to avoid conflicts.
