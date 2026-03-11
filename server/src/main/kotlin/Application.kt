package com.quadrigasoftware.portfolio_ai

import com.quadrigasoftware.configServerCore
import com.quadrigasoftware.configureCoreStatusPages
import io.github.cdimascio.dotenv.dotenv
import io.ktor.server.application.*

fun main(args: Array<String>) {
    try {
        val dotenv = dotenv {
            ignoreIfMissing = true
        }
        dotenv.entries().forEach { entry ->
            if (System.getProperty(entry.key).isNullOrBlank()) {
                System.setProperty(entry.key, entry.value)
            }
        }
    } catch (e: Exception) {
    }
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSecurity()
    configureSerialization()
    configureTemplating()
    configureRouting()

    configServerCore()
    configureCoreStatusPages()
}
