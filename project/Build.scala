import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "levaeuapp"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      "com.codahale" % "jerkson_2.9.1" % "0.5.0",
      "org.squeryl" %% "squeryl" % "0.9.5-6",
      "com.google.code.gson" % "gson" % "2.2"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      
    )

}
