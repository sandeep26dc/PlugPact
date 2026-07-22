buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // We are using 8.2.2 to match your JDK 17 setup
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
