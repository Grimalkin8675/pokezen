package pokezen

import play.api.libs.json._
import play.api.libs.functional.syntax._


case class PokemonName(name: String)

object PokemonName {
  implicit val nameWrites: Writes[PokemonName] =
    Writes(name => JsString(name.name))
}

object PokemonNameOrdering extends Ordering[PokemonName] {
  def compare(a: PokemonName, b: PokemonName): Int = a.name compare b.name
}
