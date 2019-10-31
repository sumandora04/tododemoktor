package com.notepoint.todoListRestApi

import io.ktor.application.call
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import java.time.LocalDate

fun Routing.todoApi() {
    /* route("/api/todo"){
         get("/"){
             call.respond(todos)
         }
     }*/

    /* route("/api"){
         route("/todos"){
             get {
                 call.respond(todos)
             }
         }
     }*/

    /* route("/api"){
         route("/todos", HttpMethod.Get){
             handle {
                 call.respond(todos)
             }
         }
     }*/

    /*route("/api"){
        route("/todos", HttpMethod.Get){
            handle {
                call.respond(todos)
            }
        }
        //We can do post also.
        route("/todos", HttpMethod.Post){
            handle {
                call.respond(HttpStatusCode.Unauthorized) // Currently we dont have any post method.. so show an error message
            }
        }
    }*/

    route("/api") {
        get("/todos") {
            call.respond(todos)
        }

        //Getting a specific item:
        get("/todos/{id}"){
            val id:String = call.parameters["id"]!!
            try {
                val todo:TodoItem = todos[id.toInt()]
                call.respond(todo)
            } catch (e: Throwable) {
                call.respond(HttpStatusCode.NotFound)
            }
        }

        //We can do post also.
        post("/todos") {
//            call.respond(HttpStatusCode.NotFound) // Currently we dont have any post method.. so show an error message
            val todo:TodoItem = call.receive<TodoItem>()
            val newTodoItem =
                TodoItem(todos.size+1,todo.title,todo.details,todo.dueDate,todo.importance)

            todos = todos+newTodoItem

            call.respond(HttpStatusCode.Created, todos)//return both http response and newly created list.
        }
    }
}

val todo1 = TodoItem(
    1,
    "Add rest api data access",
    "Add database support",
    LocalDate.of(2019, 12, 12),
    Importance.MEDIUM
)

val todo2 = TodoItem(
    2,
    "Add rest api service",
    "Add service to get data",
    LocalDate.of(2019, 12, 12),
    Importance.HIGH
)

var todos = listOf(todo1, todo2)