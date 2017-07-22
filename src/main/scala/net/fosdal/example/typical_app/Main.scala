package net.fosdal.example.typical_app

import com.typesafe.scalalogging.LazyLogging
import net.fosdal.example.typical_app.configuration.Configuration._
import net.fosdal.example.typical_app.metrics.Metrics
import net.fosdal.example.typical_app.metrics.Metrics._

object Main extends App with LazyLogging {

  logger.info(s"BuildInfo: ${BuildInfo.toJson}")
  logger.info(s"Configuration File:\n${rendering(BuildInfo.configBase)}")
  config.validate()
  Metrics(config.monitoring)

  val greeter = Greeter(config.greeting)
  val msg = greeter(config.name)

  while (true) {
    Metrics.timer(greetDuration) {
      Metrics.greets.mark()
      logger.info(msg)
      Thread.sleep(config.interval.toMillis)
    }
  }

}
