package util

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import client.InternetArchiveClient
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig
import org.specs2.mutable.{BeforeAfter, Specification}
import org.specs2.specification.{AfterAll, Scope}
import service.ThumbnailService

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

trait WiremockClient extends Specification with Scope with BeforeAfter with AfterAll {
  val host: String = "127.0.0.1"
  val port: Int = 8892

  private val wireMockServer = new WireMockServer(
    wireMockConfig()
      .port(port)
  )

  def before: Unit = {
    wireMockServer.start()
    WireMock.configureFor(host, port)
  }

  def after: Unit = {

  }

  def afterAll: Unit = {
    wireMockServer.stop()
  }

  implicit val system: ActorSystem = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executor: ExecutionContext = ExecutionContext.global
  implicit val completionTimeout = 1000.milliseconds

  val httpClient: InternetArchiveClient = new InternetArchiveClient(
      protocol = "http",
      host = "localhost",
      port = port
  )

  val service: ThumbnailService = new ThumbnailService(httpClient)
}