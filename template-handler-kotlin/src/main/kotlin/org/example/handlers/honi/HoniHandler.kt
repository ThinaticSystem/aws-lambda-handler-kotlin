package org.example.handlers.honi

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.logging.LogLevel
import com.thinaticsystem.awslambdahandlerkotlin.requestKotlinHandler.handlerBase.RequestKotlinHandler

@Suppress("unused")
class HoniHandler : RequestKotlinHandler<HoniInput, HoniOutput>(HoniInput::class.java) {
    companion object {
        private val TAG = this::class.simpleName!!
    }

    override fun handleRequest(input: HoniInput, context: Context): HoniOutput {
        val tag = "$TAG#handleRequest"

        context.logger.log("[$tag] Entry", LogLevel.INFO)

        val repeatedName = input.word
            .repeat(input.repeatCount)

        context.logger.log("[$tag] Exit", LogLevel.INFO)
        return HoniOutput(
            repeatedName = repeatedName,
        )
    }

    override fun handleInputReadingException(e: Exception, context: Context): HoniInput {
        context.logger.log(
            """
                Failed to read input
                Fall back to (word: "", repeatCount: 0)
            """.trimIndent(),
            LogLevel.WARN
        )
        // Fallback
        return HoniInput(
            word = "",
            repeatCount = 0,
        )
    }
}
