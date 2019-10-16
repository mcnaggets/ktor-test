package io.mc

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.html.respondHtml
import io.ktor.routing.get
import io.ktor.routing.routing
import kotlinx.html.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    routing {
        get("/") {
            call.respondHtml {
                body {
                    h1 { +"Index" }
                    for (i in 1..3) {
                        a(href = "/page/$i") { +"Link $i" }
                        br()
                    }
                    a(href = "/noindex") { +"noindex" }
                }
            }
        }

        get("/page/{number}") {
            call.respondHtml {
                val num = call.parameters["number"]
                head {
                    title { +"$num" }
                }
                body {
                    h1 { +"Page $num" }
                }
            }
        }
        get("/noindex") {
            call.respondHtml {
                head {
                    title { +"noindex" }
                    meta(name = "robots", content = "noindex")
                }
                body {
                    h1 { +"INDEX ME" }
                }
            }
        }
    }
}

