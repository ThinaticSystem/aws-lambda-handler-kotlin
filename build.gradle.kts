plugins {
    kotlin("jvm")
    id("com.github.johnrengelman.shadow")
}

group = "com.thinaticsystem"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    val awsLambdaJavaCoreVersion: String by project
    implementation("com.amazonaws:aws-lambda-java-core:$awsLambdaJavaCoreVersion")
    val jacksonModuleKotlinVersion: String by project
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonModuleKotlinVersion")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
