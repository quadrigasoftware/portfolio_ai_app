package com.quadrigasoftware.portfolio_ai

import com.quadrigasoftware.MySession
import com.quadrigasoftware.headerStyles
import com.quadrigasoftware.loginStatusHeader
import io.ktor.server.html.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import kotlinx.html.*

fun Route.testingPageRoute() {
    get("/testing-123") {
        val session = call.sessions.get<MySession>()
        call.respondHtml {
            head {
                title("Testing 123")
                headerStyles()
            }
            body {
                loginStatusHeader(session) {
                    a(href = "/", classes = "ts-menu-item") { +"Leaderboard" }
                    a(href = "/testing-123", classes = "ts-menu-item") { +"Testing Page" }
                }
                h1 {
                    +"Testing 123 Page"
                }
                p {
                    +"This page is a server-side rendered page using the reusable header component."
                }
                if (session?.userId != null) {
                    p {
                        +"You are currently logged in as ${session.userName ?: session.userId}."
                    }
                } else {
                    p {
                        +"You are not logged in."
                    }
                    div {
                        a(href = "/mock-login-action", classes = "ts-btn-nav") {
                            +"Simulate Login"
                        }
                    }
                }
            }
        }
    }

    get("/mock-login-action") {
        call.sessions.set(MySession(userId = "user-123", userName = "Mock User", email = "mock@example.com", provider = "mock"))
        call.respondRedirect("/testing-123")
    }
}
