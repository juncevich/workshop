enablePlugins(GatlingPlugin)

scalaVersion := "2.12.9"

name := "gatling"

version := "0.1"

libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % "3.3.1" % "test,it"
libraryDependencies += "io.gatling" % "gatling-test-framework" % "3.3.1" % "test,it"
