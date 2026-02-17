package com.quadrigasoftware.portfolio_ai

import com.quadrigasoftware.type_safe.shared.configServerCore
import io.ktor.server.application.*



fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSecurity()
    configureSerialization()
    configureTemplating()
    configureRouting()

    configServerCore()


}
