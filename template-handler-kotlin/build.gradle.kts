plugins {
    kotlin("jvm")
    id("com.github.johnrengelman.shadow")
}

group = "org.example"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("com.thinaticsystem.awslambdahandlerkotlin:request-kotlin-handler")
}
/*
 * TODO "request-kotlin-handler" has not been published yet
 *      Use a local source module instead of the (will be) published one.
 */
configurations.all {
    resolutionStrategy.dependencySubstitution {
        substitute(module("com.thinaticsystem.awslambdahandlerkotlin:request-kotlin-handler"))
            .using(project(":request-kotlin-handler"))
            .because("Not published yet")
    }
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
