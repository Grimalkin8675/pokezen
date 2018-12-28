package pokezen

import play.api.libs.json._
import play.api.libs.functional.syntax._


case class Name(name: String)

object Name {
  implicit val nameWrites: Writes[Name] =
    Writes((name: Name) => JsString(name.name))
}
