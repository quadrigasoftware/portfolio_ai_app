@file:Suppress("PropertyName")

val kotlin_version: String by project
val kotlinx_browser_version: String by project
val kotlinx_html_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "2.3.0"
    id("io.ktor.plugin") version "3.4.0"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.3.0"
}

application {
    mainClass = "io.ktor.server.netty.EngineMain"
}

tasks.withType<ProcessResources> {
    val wasmOutput = file("../web/build/dist/wasmJs/productionExecutable")
    if (wasmOutput.exists()) {
        inputs.dir(wasmOutput)
    }

    from("../web/build/dist/wasmJs/productionExecutable") {
        into("web")
        include("**/*")
    }
    duplicatesStrategy = DuplicatesStrategy.WARN
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    // 1. Define the file path
    val sharedJar = file("libs/server_core.jar")

    // 2. Explicitly check if it exists
    if (!sharedJar.exists()) {
        throw GradleException("\n\n❌ SHARED JAR NOT FOUND at: ${sharedJar.absolutePath}\n" +
                "Please run './gradlew :shared-core:syncSharedLib' in the Public Repo first.\n")
    }
    implementation(files(sharedJar))

    implementation("io.ktor:ktor-server-core")
    implementation("io.ktor:ktor-server-auth")
    implementation("io.ktor:ktor-server-host-common")
    implementation("io.ktor:ktor-server-status-pages")
    implementation("io.ktor:ktor-server-content-negotiation")
    implementation("io.ktor:ktor-serialization-kotlinx-json")
    implementation("io.ktor:ktor-server-html-builder")
    implementation("org.jetbrains.kotlinx:kotlinx-html:$kotlinx_html_version")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-css-jvm:2025.6.4")
    implementation("io.ktor:ktor-server-htmx")
    implementation("io.ktor:ktor-htmx-html")
    implementation("io.ktor:ktor-server-netty")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-server-config-yaml")
    testImplementation("io.ktor:ktor-server-test-host")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}
