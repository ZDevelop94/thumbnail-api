package domian

final case class VideoMetadata(metadata: Metadata)
final case class Metadata(identifier: String,
                    collection: Seq[String],
                    creator: String,
                    description: String,
                    licenseurl: String,
                    mediatype: String,
                    subject: String,
                    publicdate: String,
                    title: String,
                    uploader: String,
                    addeddate: String,
                    backup_location: String)