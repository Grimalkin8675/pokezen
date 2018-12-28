package pokezen

import play.api.libs.json._
import play.api.libs.functional.syntax._


case class Name(name: String) {
  def contains(string: String): Boolean = this.name.contains(string)
}

object Name {
  implicit val nameWrites: Writes[Name] =
    Writes((name: Name) => JsString(name.name))
}
