package pdes.anonymous

import Requests._
import scala.concurrent.duration._
import io.gatling.core.Predef.{exec, rampUsers, scenario, _}
import io.gatling.core.structure.ScenarioBuilder


class GameSimulation extends BaseSimulation {

  var number = 0
  val saves = 150 // one save per seconds => 2.5 minutes playing
  val usersPerSecond = 5

  def getUser: String = {
    number += 1
    s"""user$number"""
  }

  val swapMachine: ScenarioBuilder = scenario("Swap machine")
    .exec(_.set("user", getUser))
    .exec(getOrCreateUser("${user}"))
    .exec(createGame("${user}", "game"))
    .exec(getIdGame("${user}"))
    .repeat(saves) {
      exec(updateGame("${user}", "${gameId}")).pause(1)
    }
    .exec(deleteUser("${user}"))

  setUp(
    swapMachine.inject(constantUsersPerSec(usersPerSecond) during (1 minutes))
    //swapMachine.inject(rampUsers(users)during(600 seconds))
  ).protocols(httpProtocol)
}


