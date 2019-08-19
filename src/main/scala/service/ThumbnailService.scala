package service

import Exceptions.VideoRetrivalFailure
import client.InternetArchiveClient
import domian.Video
import client.ThumbnailGenerator._
import scala.concurrent.{ExecutionContext, Future}

class ThumbnailService(internetArchiveClient: InternetArchiveClient)(implicit executionContext: ExecutionContext){

  //two arguments service should take
  def getThumbnail(thumbnailPoint: String, downloadPath: String): Future[Video] = {
    internetArchiveClient.downloadFile(downloadPath).flatMap { video =>
      internetArchiveClient.verifyVideo(video)
    } map { video => createThumbnail(thumbnailPoint, s"${downloadPath.split("/")(2)}.avi")
      video
    } recover { case e =>
      throw VideoRetrivalFailure(e)
    }
  }
}
