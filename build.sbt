name := "zephaniahs_test"

version := "0.1"

scalaVersion := "2.13.0"

libraryDependencies ++= {
  val akkaHttpVersion             = "10.1.9"
  val akkaStreamVersion           = "2.5.23"
  val akkaHttpJsonVersion         = "1.27.0"
  val akkaHttpSprayJsonVersion    = "10.1.9"
  val json4sVersion               = "3.6.7"
  val scalaTestVersion            = "3.0.8"
  val jodaConvertVersion          = "1.8"
  val jodaTimeVersion             = "2.9.4"
  val WireMockVersion             = "2.17.0"
  val Specs2Version               = "4.7.0"

  Seq(
    "com.typesafe.akka"  %% "akka-http"               % akkaHttpVersion,
    "com.typesafe.akka"  %% "akka-stream"             % akkaStreamVersion,
    "de.heikoseeberger"  %% "akka-http-json4s"        % akkaHttpJsonVersion,
    "org.json4s"         %% "json4s-native"           % json4sVersion ,
    "org.json4s"         %% "json4s-jackson"          % json4sVersion,
    "joda-time"          %  "joda-time"               % jodaTimeVersion,
    "org.joda"           %  "joda-convert"            % jodaConvertVersion,
    "com.typesafe.akka"  %% "akka-http-spray-json"    % akkaHttpSprayJsonVersion,
    "org.specs2"         %% "specs2-core"             % Specs2Version               % "test",
    "org.specs2"         %% "specs2-mock"             % Specs2Version               % "test",
    "org.scalatest"      %% "scalatest"               % scalaTestVersion            % "test",
    "com.typesafe.akka"  %% "akka-http-testkit"       % akkaHttpVersion             % "test",
    "com.github.tomakehurst"  %  "wiremock"           % WireMockVersion             % "test"
  )
}