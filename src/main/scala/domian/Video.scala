package domian

import scala.concurrent.duration.Duration

case class Video(identifier: String, timestamp: Option[Duration], verified: Boolean)
