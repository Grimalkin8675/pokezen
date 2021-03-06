name := "pokezen-back"
version := "1.0"
scalaVersion := "2.12.8"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  guice,
  ws,
  ehcache,
  "org.reactivemongo" %% "play2-reactivemongo" % "0.16.0-play26",
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.0.0" % Test,
  "de.leanovate.play-mockws" %% "play-mockws" % "2.6.2" % Test
)
