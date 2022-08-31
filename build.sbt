import sbt.util.Level

name := "Wordle"

version := "0.1"

scalaVersion := "2.13.8"

logLevel := Level.Warn

assembly / assemblyJarName := "wordle.jar"

lazy val ammoniteVersion = "2.5.4"

libraryDependencies ++= Seq(
  "com.lihaoyi" % "ammonite" % ammoniteVersion % "test" cross CrossVersion.full
)

Test / sourceGenerators += Def.task {
  val file = (Test / sourceManaged).value / "amm.scala"
  IO.write(file,
    """|object amm extends App {
       |  ammonite.AmmoniteMain.main(args)
       |}""".stripMargin)
  Seq(file)
}.taskValue