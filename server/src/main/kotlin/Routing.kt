package com.quadrigasoftware.portfolio_ai

import com.quadrigasoftware.configureCoreAuthRoutes
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        // Static plugin. Try to access `/static/index.html`
        staticResources("/static", "static")
        staticResources("/", "web")
        configureCoreAuthRoutes(this@configureRouting)
        testingPageRoute()
    }
}
