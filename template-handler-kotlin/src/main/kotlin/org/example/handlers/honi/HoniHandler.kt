package org.example.handlers.honi

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.logging.LogLevel
import com.thinaticsystem.awslambdahandlerkotlin.requestKotlinHandler.handlerBase.RequestKotlinHandler

@Suppress("unused")
class HoniHandler : RequestKotlinHandler<HoniInput, HoniOutput>(HoniInput::class.java) {
    override fun handleRequest(input: HoniInput, context: Context): HoniOutput {
        context.logger.log("[${this::class.simpleName}#handleRequest] Entry", LogLevel.INFO)

        val repeatedName = input.name
            .repeat(input.repeatCount)

        context.logger.log("[${this::class.simpleName}#handleRequest] Exit", LogLevel.INFO)
        return HoniOutput(
            repeatedName = repeatedName,
        )
    }
}
