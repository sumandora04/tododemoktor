package com.notepoint

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.notepoint.todoListRestApi.todo1
import com.notepoint.todoListRestApi.todo2
import com.notepoint.todoListRestApi.todoApi
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.content.*
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.http.content.*
import io.ktor.jackson.jackson
import org.w3c.dom.Text

fun main(args: Array<String>): Unit = io.ktor.server.cio.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(Routing) {
        todoApi()
    }

    install(StatusPages) {
        this.exception<Throwable> { e ->
            call.respondText(e.localizedMessage, ContentType.Text.Plain)
            throw e
        }
    }

    install(ContentNegotiation){
        //Input: convert JSON to Kotlin
        //Output: convert Kotlin to JSON
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }
}

