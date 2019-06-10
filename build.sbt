import Dependencies._

enablePlugins(GatlingPlugin)

lazy val root = (project in file("."))
  .settings(
    inThisBuild(List(
      organization := "pdes",
      scalaVersion := "2.12.8",
      version := "0.1"
    )),
    name := "pdes-tp-load-testing-anonymous",
    libraryDependencies ++= gatling
  )
