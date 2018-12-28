name := "pokezen"
version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  guice,
  ws,
  ehcache,
  "org.scalacheck" %% "scalacheck" % "1.14.0" % Test
)
