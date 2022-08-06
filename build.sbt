ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "ScalaPlanExec"
  )

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.11"