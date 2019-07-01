package pdes.anonymous

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

import scala.util.Random

object Requests {
  val random = new Random

  def randomMachine(): String = {
    val machines = Seq(
      """"type":"TRANSPORTER","direction":0,"onBoard":[]""",
      """"type":"SELLER","direction":0,"toSell":[]""",
      """"type":"STARTER","direction":0,"resource":{"type":"GOLD","state":"SOLID"},"isCrafting":false""",
      """"type":"EMPTY""""
    )
    machines(random.nextInt(machines.length))
  }

  def randomMachines(): String = {
    var result = "["
    for(x <- 0 to 3) {
      for (y <- 0 to 3) {
        if (x == 0 && y == 0) {
          result += s"""{"posiotion":{"x": $x,"y":$y},${randomMachine()}}"""
        }
        else {
          result += s""",{"posiotion":{"x": $x,"y":$y},${randomMachine()}}"""
        }
      }
    }
    result + "]"
  }

  def randomGame(): String = {
    s"""{
        "state":  {
          "currentAction": { "action": null },
          "machines": ${randomMachines()},
          "width": 4,
          "height": 4
        }
    }"""
  }

  def emptyGame(gameName: String): String = {
    s"""
         {
            "name": "$gameName",
            "state": {
              "currentAction": { "action": null },
              "machines": [],
              "floor": [],
              "width": 4,
              "height": 4
            }
         }
       """
  }

  def getAllUsers: HttpRequestBuilder = {
    http("Get All Users")
      .get("/")
      .check(status.in(200, 304))
  }

  def getOrCreateUser(username: String = "nobody"): HttpRequestBuilder = {
    http("Get or Create User")
      .get(s"/$username")
      .header("Content-Type", "application/json")
      .check(status.is(200))
  }

  def deleteUser(username: String = "nobody"): HttpRequestBuilder = {
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

  def createGame(username: String = "nobody", gameName: String = "game"): HttpRequestBuilder = {
    http("Create game")
      .post(s"/$username/games")
      .header("Content-Type", "application/json")
      .body(StringBody(emptyGame(gameName))).asJson
      .check(status.is(201))
  }

  def getIdGame(username: String = "nobody"): HttpRequestBuilder = {
    http("Get game")
      .get(s"/$username")
      .header("Content-Type", "application/json")
      .check(jsonPath("$..games[0]._id").find.saveAs("gameId"))
  }

  def updateGame(username: String = "nobody", gameId: String = "nobody-gameId"): HttpRequestBuilder = {
    http("Update game")
      .put(s"/$username/games/$gameId")
      .header("Content-Type", "application/json")
      .body(StringBody(randomGame()))
      .check(status.is(200))
  }
}

