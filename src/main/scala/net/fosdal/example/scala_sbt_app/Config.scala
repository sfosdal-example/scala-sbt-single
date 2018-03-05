package net.fosdal.example.scala_sbt_app

import com.typesafe.config.{ConfigFactory, ConfigRenderOptions}
import com.typesafe.scalalogging.LazyLogging
import pureconfig.loadConfigOrThrow

import scala.concurrent.duration.FiniteDuration

object Config extends LazyLogging {

  lazy val config: Config = loadConfigOrThrow[Config](BuildInfo.namespace)

  case class Config(greeting: String, name: String, interval: FiniteDuration, monitoring: MonitoringConfig) {

    lazy val rendering: String = rendering(BuildInfo.namespace)

    def rendering(path: String): String = {
      ConfigFactory
        .load()
        .getConfig(path)
        .root
        .render(ConfigRenderOptions.concise.setFormatted(true))
    }

  }

  case class MonitoringConfig(console: ConsoleConfig, log: LogConfig, jmx: JmxConfig)

  case class ConsoleConfig(enabled: Boolean, interval: FiniteDuration)

  case class LogConfig(enabled: Boolean, interval: FiniteDuration, logger: String)

  case class JmxConfig(enabled: Boolean, domain: String)

}
