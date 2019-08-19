import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import client.InternetArchiveClient
import service.ThumbnailService

import scala.concurrent.ExecutionContext

object Main {
  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem = ActorSystem()
    implicit val materializer: ActorMaterializer = ActorMaterializer()
    implicit val executor: ExecutionContext = ExecutionContext.global

    val httpClient: InternetArchiveClient = new InternetArchiveClient(
      protocol = "https",
      host = "archive.org",
      port = 1
    )

    val service: ThumbnailService = new ThumbnailService(httpClient)

    println("Enter downloadPath without host")
    val downloadPathMain = scala.io.StdIn.readLine()

    println("Enter the point you want thumbnail from (in seconds)")
    val seconds = scala.io.StdIn.readLine()
    service.getThumbnail(seconds, downloadPathMain)
  }
}
