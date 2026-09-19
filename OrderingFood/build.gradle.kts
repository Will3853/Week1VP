plugins {
    id("java-library")
    id("application")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

application {
    mainClass.set("com.william.test1.MainKt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}
