package scala.errorLogging

import org.slf4j.LoggerFactory
import ch.qos.logback.core.util.StatusPrinter
import ch.qos.logback.classic.LoggerContext

object SaveLogFileInLocal {

  def main(args: Array[String]): Unit = {
    def logger = LoggerFactory.getLogger(this.getClass)

    println("Hi test successful...!")
    var key = 1
    var value = 2

    //UnComment below line before trying to save log file in local directory.
    StatusPrinter.print((LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]))
    logger.info("logging key here {} and value here {} ", key, value)
  }


}
