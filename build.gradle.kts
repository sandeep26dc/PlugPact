buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // This is the core engine version
        classpath("com.android.tools.build:gradle:8.2.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
