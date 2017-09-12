name :="Project"

version := "1.0"

scalaVersion := "2.11.8"

resolvers += "Apache Snapshot Repository" at "https://repository.apache.org/snapshots"

libraryDependencies ++= Seq(

  "com.typesafe.akka" %% "akka-actor" % "2.5.3",
  "com.typesafe.akka" %% "akka-testkit" % "2.5.3" % Test,
  "com.typesafe.akka" %% "akka-stream" % "2.5.3",
  "com.typesafe.akka" %% "akka-stream-testkit" % "2.5.3" % Test,
  
  "org.scalikejdbc" %% "scalikejdbc" % "3.0.2",
  
  "org.scalikejdbc" %% "scalikejdbc-config"  % "3.0.2",
 
  "com.typesafe.slick" % "slick_2.11" % "3.2.1",

  "org.slf4j" % "slf4j-simple" % "1.7.25",

  "org.slf4j" % "slf4j-api" % "1.7.25"
  
)
