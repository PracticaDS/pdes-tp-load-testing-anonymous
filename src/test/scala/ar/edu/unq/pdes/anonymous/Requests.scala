package ar.edu.unq.pdes.anonymous

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Requests {

  def getAllUsers() = {
    http("Get All Users")
      .get("/")
      .check(status.is(200))
  }

  def getOrCreateUser(username: String = "nobody") = {
    http("Get or Create User")
      .get(s"/$username")
      .header("Content-Type", "application/json")
      .check(status.is(200))
  }

  def deleteUser(username: String = "nobody") = {
    http("Delete User")
      .delete(s"/$username")
      .check(status.is(204))
  }

//  def markAsDone(sessionAttributeName: String) = {
//    http("Mark as Done")
//      .put(s"/api/todos/$${${sessionAttributeName}}")
//      .header("Content-Type", "application/json")
//      .body(StringBody(s"""{"description": "aTodo", "done": true}"""))
//      .check(status.is(200))
//  }
}