name := """levaeuApp"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

resolvers += "repo.codahale.com" at "http://repo.codahale.com"
 
libraryDependencies += "com.codahale" % "jerkson_2.9.1" % "0.5.0"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws
)
