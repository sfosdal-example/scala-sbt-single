package net.fosdal.example.scala_sbt_app

import com.typesafe.scalalogging.LazyLogging
import net.fosdal.example.scala_sbt_app.Config.config._
import net.fosdal.example.scala_sbt_app.metrics.Metrics
import net.fosdal.example.scala_sbt_app.metrics.Metrics._

object Main extends App with LazyLogging {

  logger.info(s"BuildInfo: ${BuildInfo.toJson}")
  logger.info(s"Configuration File:\n$rendering")

  Metrics(monitoring)

  val greeter = Greeter(greeting)
  val msg     = greeter(name)

  while (true) {
    timer(greetDuration) {
      greets.mark()
      logger.info(msg)
      Thread.sleep(interval.toMillis)
    }
  }

}
