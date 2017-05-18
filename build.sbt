name := "Crawler"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "6.0.6",
  "org.scalaz.stream" % "scalaz-stream_2.11" % "0.8.6",
  "org.scala-lang" % "scala-library" % scalaVersion.value,
  "org.apache.spark" % "spark-core_2.11" % "2.1.0",
  "org.apache.spark" % "spark-sql_2.11" % "2.1.0",
  "org.apache.spark" % "spark-graphx_2.11" % "2.1.0",
  "org.apache.spark" % "spark-mllib_2.11" % "2.1.0",
  "org.apache.spark" % "spark-mllib-local_2.11" % "2.1.0",
  "org.apache.spark" % "spark-streaming_2.11" % "2.1.0",
  "commons-io" % "commons-io" % "2.5",
  "com.typesafe" % "config" % "1.3.1",
  "junit" % "junit" % "4.12",
  "org.specs2" % "specs2-core_2.11" % "3.8.9",
  "org.scalactic" %% "scalactic" % "3.0.1",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "org.specs2" % "specs2-junit_2.11" % "3.8.9",
  "org.apache.logging.log4j" % "log4j-api" % "2.8.2",
  "org.apache.logging.log4j" % "log4j-core" % "2.8.2",
  "org.apache.logging.log4j" % "log4j-api-scala_2.11" % "2.8.2",
  "log4j" % "log4j" % "1.2.17",
  "com.typesafe" % "config" % "1.3.1",
  "com.danielasfregola" %% "twitter4s" % "5.1",
  "org.twitter4j" % "twitter4j-core" % "4.0.6",
  "net.liftweb" % "lift-json_2.11" % "2.6-M4",
  "net.sourceforge.htmlcleaner" % "htmlcleaner" % "2.6.1",
  "org.json4s" % "json4s-native_2.11" % "3.5.2"
)
