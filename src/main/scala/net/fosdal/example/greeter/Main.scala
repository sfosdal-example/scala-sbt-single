package net.fosdal.example.greeter

import com.typesafe.scalalogging.LazyLogging
import net.fosdal.example.greeter.configuration.Configuration._
import net.fosdal.example.greeter.metrics.Metrics
import net.fosdal.example.greeter.metrics.Metrics._

object Main extends App with LazyLogging {

  logger.info(s"BuildInfo: ${BuildInfo.toJson}")
  logger.info(s"Configuration File:\n${rendering(BuildInfo.configBase)}")
  config.validate()
  Metrics(config.monitoring)

  val greeter = Greeter(config.greeting)
  val msg = greeter.greet(config.name)

  while (true) {
    Metrics.timer(greetDuration) {
      Metrics.greets.mark()
      logger.info(msg)
      Thread.sleep(config.interval.toMillis)
    }
  }

}
