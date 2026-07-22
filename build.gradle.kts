// ROOT BUILD FILE - Strictly for Buildscript Classpath
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

// NO "allprojects" block here. Repositories are managed in settings.gradle.kts
