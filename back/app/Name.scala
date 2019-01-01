package pokezen

import play.api.libs.json._
import play.api.libs.functional.syntax._


case class Name(name: String)

object Name extends Ordering[Name] {
  implicit def compare(a: Name, b: Name): Int = a.name compare b.name

  implicit val nameWrites: Writes[Name] =
    Writes((name: Name) => JsString(name.name))
}
