package client

import sys.process._
import scala.language.postfixOps
import org.joda.time.LocalTime

object ThumbnailGenerator {
  def createThumbnail(seconds: String, fileName: String) = {
    val timestamp = new LocalTime(seconds).formatted("HH:MM:SS")
    println(s"The command: ffmpeg -i $fileName -ss $seconds -vframes 1 $fileName.png")
    s"ffmpeg -i $fileName -ss $seconds -vframes 1 picture-$fileName.png" !
  }
}
