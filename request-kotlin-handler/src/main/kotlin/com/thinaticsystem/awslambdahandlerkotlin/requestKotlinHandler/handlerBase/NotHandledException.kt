package com.thinaticsystem.awslambdahandlerkotlin.requestKotlinHandler.handlerBase

/**
 * Exception for wrapping uncaught Exceptions.
 */
class NotHandledException(
    message: String,
    cause: Throwable,
) : Exception(
    message = message,
    cause = cause,
)
