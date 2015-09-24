name := """play-first-app"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)


scalaVersion := "2.11.6"

conflictWarning := ConflictWarning.disable

libraryDependencies ++= Seq(
    jdbc,
    cache,
    ws,
    specs2 % Test,
    "com.typesafe.play" %% "play-slick" % "1.0.1",
    "com.typesafe.slick" %% "slick-extensions" % "3.0.0"
)

//libraryDependencies += "com.oracle" % "ojdbc14" % "10.2.0.4.0" from "lib/ojdbc14-10.2.0.jar"



resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
resolvers += "Typesafe Releases" at "http://repo.typesafe.com/typesafe/maven-releases/"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
