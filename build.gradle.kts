buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.gradle}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
        classpath("com.google.gms:google-services:${Versions.googleServices}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.4.2")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}