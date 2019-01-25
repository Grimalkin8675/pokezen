package pokezen.models

import play.api.libs.json._


case class ImageURL(url: String)

object ImageURL {
  implicit val imageWrites: Writes[ImageURL] =
    Writes(image => JsString(image.url))
}
