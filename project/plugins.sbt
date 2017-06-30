logLevel := Level.Warn

addSbtPlugin("com.eed3si9n"      % "sbt-assembly"           % "0.14.5")
addSbtPlugin("com.eed3si9n"      % "sbt-buildinfo"          % "0.7.0")
addSbtPlugin("com.geirsson"      % "sbt-scalafmt"           % "0.6.8")
addSbtPlugin("com.github.gseitz" % "sbt-release"            % "1.0.5")
addSbtPlugin("com.timushev.sbt"  % "sbt-updates"            % "0.3.1")
addSbtPlugin("com.typesafe.sbt"  % "sbt-git"                % "0.9.3")
addSbtPlugin("net.virtual-void"  % "sbt-dependency-graph"   % "0.8.2")
addSbtPlugin("org.scalastyle"    %% "scalastyle-sbt-plugin" % "0.8.0")
addSbtPlugin("org.scoverage"     % "sbt-scoverage"          % "1.5.0")

// TODO periodically check for resolved sbt-git issue:
// see: https://github.com/sbt/sbt-git#known-issues
libraryDependencies += "org.slf4j" % "slf4j-nop" % "1.7.25"
