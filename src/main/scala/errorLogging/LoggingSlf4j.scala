package errorLogging

import org.slf4j.LoggerFactory

object LoggingSlf4j {

  def main(args: Array[String]): Unit = {
    def logger = LoggerFactory.getLogger(this.getClass)

    var key = 1
    var value = 2
    logger.info("logging key here {} and value here {} ", key, value)
  }

}
