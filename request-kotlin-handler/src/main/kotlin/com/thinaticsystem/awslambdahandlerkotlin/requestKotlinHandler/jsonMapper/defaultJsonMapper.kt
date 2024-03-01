package com.thinaticsystem.awslambdahandlerkotlin.requestKotlinHandler.jsonMapper

import com.fasterxml.jackson.module.kotlin.jsonMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule

val defaultJsonMapper = jsonMapper {
    addModule(kotlinModule())
}
