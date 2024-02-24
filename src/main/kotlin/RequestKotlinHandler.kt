package com.thinaticsystem

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestStreamHandler
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.kotlin.jsonMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import java.io.InputStream
import java.io.OutputStream

abstract class RequestKotlinHandler<I, O>(
    private val inputClazz: Class<I>,
    private val jsonMapper: JsonMapper = defaultJsonMapper,
) : RequestStreamHandler {
    companion object {
        val defaultJsonMapper = jsonMapper {
            addModule(kotlinModule())
        }
    }

    abstract fun handleRequest(input: I): O

    override fun handleRequest(
        inputStream: InputStream,
        outputStream: OutputStream,
        context: Context,
    ) {
        val input = this.jsonMapper.readValue(inputStream, this.inputClazz)
        val output = this.handleRequest(input)
        this.jsonMapper.writeValue(outputStream, output)
    }
}
