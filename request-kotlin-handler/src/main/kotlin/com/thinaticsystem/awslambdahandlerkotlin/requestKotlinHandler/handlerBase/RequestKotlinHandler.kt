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
        // Read and Deserialize input
        val input = try {
            this.jsonMapper.readValue(inputStream, this.inputClazz)
        } catch (e: Exception) {
            this.handleInputReadingException(e, context)
        }

        // Process by overrider
        val output = this.handleRequest(input, context)

        // Serialize and Write output
        try {
            this.jsonMapper.writeValue(outputStream, output)
        } catch (e: Exception) {
            this.handleOutputWritingException(e, context)
        }
    }

    /**
     * Override if you want to handle exceptions thrown when reading input.
     *
     * @param exception thrown by [JsonMapper.readValue]
     * @param context Lambda handler context
     *
     * @return Fallback input value.
     * If you don't want to provide a fallback, you can throw an exception without returning anything.
     *
     * @throws NotHandledException In the default implementation, all exceptions are wrapped in a [NotHandledException] and thrown.
     */
    open fun handleInputReadingException(exception: Exception, context: Context): I {
        throw NotHandledException("Failed to read input", exception)
    }

    /**
     * Override if you want to handle exceptions thrown when writing output.
     *
     * @param exception thrown by [JsonMapper.writeValue]
     * @param context Lambda handler context
     *
     * @return Fallback output value.
     * If you don't want to provide a fallback, you can throw an exception without returning anything.
     *
     * @throws NotHandledException In the default implementation, all exceptions are wrapped in a [NotHandledException] and thrown.
     */
    open fun handleOutputWritingException(exception: Exception, context: Context): I {
        throw NotHandledException("Failed to write output", exception)
    }
}
