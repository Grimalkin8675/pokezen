package pokezen.services.pokeapi

import play.api.libs.json._
import play.api.libs.functional.syntax._

import pokezen.models.PokemonName


class PokeAPIPokemonName(name: String) extends PokemonName(name)

object PokeAPIPokemonName {
  def apply(name: String): PokeAPIPokemonName = new PokeAPIPokemonName(name)

  implicit val pokeAPIPokemonNameReads: Reads[PokeAPIPokemonName] =
    (__ \ "name").read[String].map(PokeAPIPokemonName.apply)
}
