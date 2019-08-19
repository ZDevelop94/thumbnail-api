package util

import com.github.tomakehurst.wiremock.client.WireMock._
import com.github.tomakehurst.wiremock.stubbing.StubMapping

import scala.io.Source

trait FakeServices {

  def readResourceFile(path: String): String = {
    Source.fromFile(path).mkString
  }

  def getStub(path: String, resource: String, statusCode: Int = 200): StubMapping = {
    stubFor(get(urlEqualTo(path))
      .willReturn(
        aResponse()
          .withHeader("Content-type", "application/json")
          .withBody(readResourceFile(resource))
          .withStatus(statusCode)
      )
    )
  }
}
