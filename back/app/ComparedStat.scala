package pokezen

import play.api.libs.json._
import play.api.libs.json.Json.JsValueWrapper


case class ComparedStat(name: String, comparisons: Map[Type, Double])

object ComparedStat {
  implicit val mapWrites: Writes[Map[Type, Double]] = {
    val comparisonToKeyValue: ((Type, Double)) => (String, JsValueWrapper) = {
      case (pokeType: Type, difference: Double) =>
        pokeType.typeName -> JsNumber(difference)
    }

    def comparisonsToJsObject(comparisons: Map[Type, Double]): JsObject =
      Json.obj(comparisons.map(comparisonToKeyValue).toSeq: _*)

    Writes(comparisonsToJsObject)
  }

  implicit val comparedStats: Writes[ComparedStat] = Json.writes[ComparedStat]
}
