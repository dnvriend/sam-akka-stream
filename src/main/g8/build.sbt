
lazy val `$name$` = (project in file("."))
  .settings(
	libraryDependencies += "com.github.dnvriend" %% "sam-annotations" % "1.0.22",
    libraryDependencies += "com.github.dnvriend" %% "sam-lambda" % "1.0.22",
    libraryDependencies += "com.github.dnvriend" %% "sam-akka-stream" % "1.0.22",
    libraryDependencies += "com.amazonaws" % "aws-lambda-java-core" % "1.2.0",
    libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.8",
    libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.5.8",
    libraryDependencies += "com.typesafe.akka" %% "akka-slf4j" % "2.5.8",
    libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3",
    resolvers += Resolver.bintrayRepo("dnvriend", "maven"),
    scalaVersion := "2.12.4",
	  samStage := "$stage$",
	  organization := "$organization$",
	  description := "sam with akka-stream"
  )

  

