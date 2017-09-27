lazy val root = (project in file(".")) settings (
  inThisBuild(
    Seq(
      organization := "org.example",
      scalaVersion := "2.12.3",
      version := "0.0.1"
    )
  ),
  name := "showtree",
  libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.0.+" % Test
  )
)
