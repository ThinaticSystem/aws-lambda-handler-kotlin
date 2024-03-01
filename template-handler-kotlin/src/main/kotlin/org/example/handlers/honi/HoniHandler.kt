package org.example.handlers.honi

import com.thinaticsystem.awslambdahandlerkotlin.requestKotlinHandler.handlerBase.RequestKotlinHandler

@Suppress("unused")
class HoniHandler : RequestKotlinHandler<HoniInput, HoniOutput>(HoniInput::class.java) {
    override fun handleRequest(input: HoniInput): HoniOutput {
        return HoniOutput(
            repeatedName = input.name
                .repeat(input.repeatCount),
        )
    }
}
