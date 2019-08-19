package Exceptions

case class InvalidHttpStatus(status: Int) extends Exception(s"Invalid status: $status")
case class FailedUnmarshal(e: Throwable) extends Exception(s"Failed to unmarshal response with error: ${e.getMessage}")
case class VideoRetrivalFailure(e: Throwable) extends Exception(s"Failed to retrieve Video with message: ${e.getMessage}")
