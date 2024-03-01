package com.thinaticsystem.awslambdahandlerkotlin.requestKotlinHandler.jsonMapper

import com.fasterxml.jackson.module.kotlin.jsonMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule

/**
 * [kotlinModule] installed [com.fasterxml.jackson.databind.json.JsonMapper]
 */
val defaultJsonMapper = jsonMapper {
    addModule(kotlinModule())
}
