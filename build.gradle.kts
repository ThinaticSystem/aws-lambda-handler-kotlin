plugins {
    kotlin("jvm")
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
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
