pluginManagement {
    plugins {
        val kotlinVersion: String by settings
        kotlin("jvm") version kotlinVersion
        val shadowVersion: String by settings
        id("com.github.johnrengelman.shadow") version shadowVersion
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "template-aws-lambda-handler-kotlin"
