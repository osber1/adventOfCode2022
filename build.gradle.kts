import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
    id("io.kotest") version "0.3.9"
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
    application
}

group = "io.osber"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("io.ktor:ktor-client-core:2.1.3")
    implementation("io.ktor:ktor-client-apache:2.1.3")

    testImplementation(kotlin("test"))
    testImplementation(platform("io.kotest:kotest-bom:5.5.4"))
    testImplementation("io.kotest:kotest-runner-junit5")
    testImplementation("io.kotest:kotest-property")
    testImplementation("org.amshove.kluent:kluent:1.72")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}
