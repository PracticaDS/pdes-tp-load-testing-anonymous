package pdes.anonymous

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

abstract class BaseSimulation extends Simulation {
  // Here is the root for all relative URLs
  val baseUrl = "http://localhost:8080"
  val headers = "application/json,text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"

  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl(baseUrl)
    .acceptHeader(headers)
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")
}