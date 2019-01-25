package pokezen.models

import play.api.libs.json._


case class Stat(name: String, value: Double)

object Stat {
  implicit val statWrites: Writes[Stat] = Json.writes[Stat]
}
