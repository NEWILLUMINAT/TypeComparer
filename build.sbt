ThisBuild / version := "0.1.15-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.14"

lazy val root = (project in file("."))
  .settings(
    name := "simple",
    libraryDependencies += "org.scala-lang" % "scala-compiler" % "2.13.14"

  )
