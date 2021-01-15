
// The simplest possible sbt build file is just one line:

ThisBuild / scalaVersion := "2.13.3"
ThisBuild / organization := "ch.epfl.scala"
ThisBuild / version := "1.0"

 lazy val root = (project in file(".")).
   settings(
      name := "textGame",
      // https://mvnrepository.com/artifact/org.postgresql/postgresql
      libraryDependencies += "org.postgresql" % "postgresql" % "42.2.18"
   )
// To learn more about multi-project builds, head over to the official sbt
// documentation at http://www.scala-sbt.org/documentation.html

