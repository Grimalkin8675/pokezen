package pokezen

import play.api.libs.json._
import play.api.libs.functional.syntax._


case class PokemonNames(names: PokemonName*) {
  def sorted: PokemonNames =
    PokemonNames(this.names.sorted(PokemonNameOrdering): _*)
}

object PokemonNames {
  implicit val namesWrites: Writes[PokemonNames] =
    Writes((names: PokemonNames) => Json.toJson(names.names))
}