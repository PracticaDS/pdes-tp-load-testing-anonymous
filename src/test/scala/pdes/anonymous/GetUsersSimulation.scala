package pdes.anonymous

import Requests._
import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

class GetUsersSimulation extends BaseSimulation {

  val scn: ScenarioBuilder = scenario("Get All Users Load Testing")
    .exec(_.set("users", 1 to 10))

    .foreach("${users}", "i") {
      exec(getOrCreateUser("user${i}").silent)
    }
    .repeat(5) {
      exec(getAllUsers()).pause(2 seconds)
    }
    .foreach("${users}", "i") {
      exec(deleteUser("user${i}").silent)
    }

  setUp(
    scn.inject(rampUsers(200)during(10 seconds))
  ).protocols(httpProtocol)
}
