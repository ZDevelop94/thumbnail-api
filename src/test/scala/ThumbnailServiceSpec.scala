import domian.Video
import scala.concurrent.duration._
import scala.concurrent.Await
import util.{FakeServices, WiremockClient}

class ThumbnailServiceSpec extends WiremockClient with  FakeServices {
  //this spec is really an integration test but i am sure packaging order doesn't matter for this exercise
  //Video(timestamp: Duration, downloadPath: String, verified:)
  val videoExpected = Video("electricsheep-flock-244-32500-4", Some(70.seconds), true)
  val unVerifiedVideoExpected = videoExpected.copy(verified = false)
  val downloadPath = "/download/electricsheep-flock-244-32500-4/00244%3D32664%3D22973%3D23653.avi"
  val metadataPath = "/metadata/electricsheep-flock-244-32500-4"

  sequential
  "ThumbnailService" should {
    "return a video's metadata" in {
      //if video is returned I know other processes are successful i.e the download and verification

      getStub(metadataPath, "/Users/flynnz01/Documents/Workspace/zephaniahs_test/src/test/scala/resources/electric_sheep_metedata_response.json")
      val video: Video = Await.result(service.getThumbnail("2",downloadPath), 5.seconds)
      video.identifier must be equalTo videoExpected.identifier
    }

    "return an invalid Video" in {
      //if video is returned I know other processes are successful i.e the download and verification
      getStub(metadataPath, "/Users/flynnz01/Documents/Workspace/zephaniahs_test/src/test/scala/resources/electric_sheep_metedata_response.json")
      val video: Video = Await.result(service.getThumbnail("2", downloadPath), 5.seconds)
      video.verified must be equalTo unVerifiedVideoExpected.verified
    }
  }
}
