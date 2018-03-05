name := "scala-sbt-app"

organization := "net.fosdal.example"

scalaVersion := "2.12.4"

fork := true

libraryDependencies ++= {
  lazy val Log4JVersion   = "2.10.0"
  lazy val MetricsVersion = "4.0.2"
  Seq(
    "com.github.pureconfig"      %% "pureconfig"                  % "0.9.0",
    "com.github.melrief"         %% "purecsv"                     % "0.1.1",
    "com.typesafe.scala-logging" %% "scala-logging"               % "3.8.0",
    "io.dropwizard.metrics"      % "metrics-core"                 % MetricsVersion,
    "io.dropwizard.metrics"      % "metrics-healthchecks"         % MetricsVersion,
    "io.dropwizard.metrics"      % "metrics-jmx"                  % MetricsVersion,
    "io.dropwizard.metrics"      % "metrics-jvm"                  % MetricsVersion,
    "io.dropwizard.metrics"      % "metrics-log4j2"               % MetricsVersion,
    "joda-time"                  % "joda-time"                    % "2.9.9",
    "org.apache.logging.log4j"   % "log4j-api"                    % Log4JVersion,
    "org.apache.logging.log4j"   % "log4j-core"                   % Log4JVersion,
    "org.apache.logging.log4j"   % "log4j-slf4j-impl"             % Log4JVersion,
    "org.joda"                   % "joda-convert"                 % "2.0",
    "org.scalacheck"             %% "scalacheck"                  % "1.13.5" % Test,
    "org.scalamock"              %% "scalamock-scalatest-support" % "3.6.0" % Test,
    "org.scalatest"              %% "scalatest"                   % "3.0.5" % Test,
    "org.slf4j"                  % "slf4j-api"                    % "1.7.25"
  )
}

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding",
  "UTF-8",
  "-feature",
  "-unchecked",
  "-Xfatal-warnings",
  "-Xfuture",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-unused",
  "-Ywarn-value-discard"
)

//
// Plugin Settings: sbt-buildinfo
//
enablePlugins(BuildInfoPlugin)
buildInfoOptions ++= Seq(BuildInfoOption.BuildTime, BuildInfoOption.ToJson, BuildInfoOption.ToMap)
buildInfoPackage := Seq(organization.value, name.value)
  .mkString(".")
  .replaceAll("[^a-zA-Z0-9.]", "_")
  .replaceAll("^[0-9]*", "")
buildInfoKeys := BuildInfoKey.ofN(
  name,
  version,
  scalaVersion,
  sbtVersion,
  description,
  isSnapshot,
  resolvers,
  libraryDependencies,
  scalacOptions in (Compile, compile),
  git.gitCurrentBranch,
  git.gitCurrentTags,
  git.gitUncommittedChanges,
  git.formattedShaVersion,
  git.formattedDateVersion,
  git.gitHeadCommit,
  BuildInfoKey.action("namespace")(buildInfoPackage.value)
)

//
// Plugin Settings: sbt-scoverage
//
coverageExcludedPackages := """.*\.BuildInfo"""
coverageMinimum := 4
coverageFailOnMinimum := true

//
// Plugin Settings: scalastyle-sbt-plugin
//
scalastyleFailOnError := true

//
// Plugin Settings: sbt-assembly
//
test in assembly := {}

assemblyMergeStrategy in assembly := {
  case "reference.conf" => MergeStrategy.concat
  case PathList("META-INF", xs @ _*) =>
    xs.map(_.toLowerCase) match {
      case ("manifest.mf" :: Nil) | ("index.list" :: Nil) | ("dependencies" :: Nil) =>
        MergeStrategy.discard
      case ps @ (e :: es) if ps.last.endsWith(".sf") || ps.last.endsWith(".dsa") =>
        MergeStrategy.discard
      case _ => MergeStrategy.discard
    }
  case _ => MergeStrategy.first
}

//
// Plugin Settings: sbt-git
//
enablePlugins(GitBranchPrompt)
