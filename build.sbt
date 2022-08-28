import sbt.util.Level

name := "Wordle"

version := "0.1"

scalaVersion := "2.13.8"

logLevel := Level.Warn

assembly / assemblyJarName := "wordle.jar"