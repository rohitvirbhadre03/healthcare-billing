import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm") version "2.4.21"
    id("com.google.devtools.ksp") version "2.3.13"
    id("io.micronaut.application") version "4.6.2"
}

group = "com.linx"
version = "0.1.0"

repositories {
    mavenCentral()
}

micronaut {
    version("4.10.22")
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("com.linx.billing.*")
    }
}

application {
    mainClass.set("com.linx.billing.ApplicationKt")
}

dependencies {
    ksp("io.micronaut.serde:micronaut-serde-processor")
    implementation("io.micronaut.serde:micronaut-serde-jackson")
    runtimeOnly("ch.qos.logback:logback-classic")
    testImplementation(kotlin("test"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_21)
    }
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}
