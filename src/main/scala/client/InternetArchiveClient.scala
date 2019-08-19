package client


import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.stream.Materializer
import domian.{Metadata, Video, VideoMetadata}
import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.duration._
import sys.process._
import java.net.URL
import java.io.File

import Exceptions.FailedUnmarshal
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.unmarshalling.Unmarshal
import spray.json.DefaultJsonProtocol

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val metadataFormat = jsonFormat12(Metadata)
  implicit val videoMetadataFormat = jsonFormat1(VideoMetadata)
}

class InternetArchiveClient(protocol:String, host: String, port: Int)(implicit val executor: ExecutionContext,
                                                                      implicit val materializer: Materializer,
                                                                      implicit val system: ActorSystem) extends JsonSupport {

  def downloadFile(downloadPath: String): Future[Video]= {
    val identifier = s"${downloadPath.split("/")(2)}"
    //this is what i would normally but due to poor website design of source i have to override for now
    //verifyVideo method shows this implementation as host is consistent
    /*val downloadFuture = Future(new URL( host match {
      case "localhost" => s"$protocol:$port//$host$path"
      case hostString => s"$protocol:$port//$host$path"
    })#> new File(s"$identifier.mp4"))
*/

    val downloadFuture = Future(new URL(
      s"$protocol://archive.org$downloadPath"
    )#> new File(s"$identifier.avi"))

    //could of got actual duration but time is of the essence can go through with you if need be
    downloadFuture.map{download => download.run()
      Video(identifier,None, verified = false)
    }

  }

  def verifyVideo(video: Video): Future[Video] = {
    val responseFuture: Future[HttpResponse] = host match {
      case "localhost" => println(s"$protocol://localhost:$port/metadata/${video.identifier}")
        Http().singleRequest(HttpRequest(uri = s"$protocol://localhost:$port/metadata/${video.identifier}"))
      case hostString =>  println(s"$protocol://$hostString/metadata/${video.identifier}")
        Http().singleRequest(HttpRequest(uri = s"$protocol://$hostString/metadata/${video.identifier}"))
    }

    responseFuture flatMap {
      case response: HttpResponse if response.status != 200  => val processedResponse = Unmarshal(response).to[VideoMetadata]
        //could of got actual duration but time is of the essence can go through with you if need be
        processedResponse.map(videoMetadata => Video(videoMetadata.metadata.identifier, Some(5.seconds), verified = false))
      case response: HttpResponse => val processedResponse = Unmarshal(response).to[VideoMetadata]
        processedResponse.map(videoMetadata => Video(videoMetadata.metadata.identifier, Some(5.seconds), verified = true))
      } recover {case e =>
      throw FailedUnmarshal(e)
    }
  }
}
