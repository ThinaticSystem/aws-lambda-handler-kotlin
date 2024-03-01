package com.thinaticsystem.awslambdahandlerkotlin.requestKotlinHandler.handlerBase

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestStreamHandler
import com.fasterxml.jackson.databind.json.JsonMapper
import com.thinaticsystem.awslambdahandlerkotlin.requestKotlinHandler.jsonMapper.defaultJsonMapper
import java.io.InputStream
import java.io.OutputStream

abstract class RequestKotlinHandler<I, O>(
    private val inputClazz: Class<I>,
    private val jsonMapper: JsonMapper = defaultJsonMapper,
) : RequestStreamHandler {
    abstract fun handleRequest(input: I, context: Context): O

    final override fun handleRequest(
        inputStream: InputStream,
        outputStream: OutputStream,
        context: Context,
    ) {
        val input = this.jsonMapper.readValue(inputStream, this.inputClazz)
        val output = this.handleRequest(input, context)
        this.jsonMapper.writeValue(outputStream, output)
    }
}
