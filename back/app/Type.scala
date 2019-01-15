package pokezen

import play.api.libs.json._


case class Type(typeName: String)

object Type {
  implicit val typeWrites: Writes[Type] =
    Writes(pokeType => JsString(pokeType.typeName))
}
