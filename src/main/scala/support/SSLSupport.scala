package support

import java.io.{File, FileInputStream}
import java.security.{KeyStore, SecureRandom}
import javax.net.ssl._

import scala.util.Try

//thought i needed SSL support but amandond implementation
/*trait SSLSupport {
  val SSLContextTypeDefault = "TLS"

  val trustManagerPath: String = ""
  val trustManagerPassword: String = "changeit"
  val trustManagerType: String = "JKS"
  val keyManagerPath: String = ""
  val keyManagerPassword: String = ""
  val keyManagerType: String = ""
  val keyStoreType: String = ""

  lazy val fallbackSSLContext: SSLContext = {
    val context = SSLContext.getInstance(SSLContextTypeDefault)
    context.init(Array(), Array(), new SecureRandom())
    context
  }

  val sslContext: SSLContext = {
    create().getOrElse(fallbackSSLContext)
  }

  private def getKeyStoreManagers: Array[KeyManager] = {
    var keyInput: FileInputStream = null // scalastyle:ignore
    try {
      val keyStore = KeyStore.getInstance(keyManagerType)
      keyInput = new FileInputStream(new File(keyManagerPath))
      val keyManagerFactory = KeyManagerFactory.getInstance(keyStoreType)
      keyStore.load(keyInput, keyManagerPassword.toCharArray)
      keyManagerFactory.init(keyStore, keyManagerPassword.toCharArray)
      keyManagerFactory.getKeyManagers
    } finally {
      if (keyInput != null) {  // scalastyle:ignore
        keyInput.close()
      }
    }
  }

  private def getTrustStoreManagers: Array[TrustManager] = {
    var trustStoreInput: FileInputStream = null // scalastyle:ignore
    try {
      val trustStore = KeyStore.getInstance(trustManagerType)
      trustStoreInput = new FileInputStream(new File(trustManagerPath))
      val trustManagerFactory = TrustManagerFactory.getInstance(keyStoreType)
      trustStore.load(trustStoreInput, trustManagerPassword.toCharArray)
      trustManagerFactory.init(trustStore)
      trustManagerFactory.getTrustManagers
    } finally {
      if (trustStoreInput != null) {  // scalastyle:ignore
        trustStoreInput.close()
      }
    }
  }

  private def create(): Try[SSLContext] = {
    Try {
      try {
        val context = SSLContext.getInstance(SSLContextTypeDefault)
        context.init(getKeyStoreManagers, getTrustStoreManagers, new SecureRandom())
        context
      } catch {
        case ex: Throwable =>
          ex.printStackTrace()
          throw ex
      }
    }
  }
}*/
