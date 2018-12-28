package pokezen.services.pokeapi

import play.api.libs.json._
import play.api.libs.functional.syntax._

import pokezen.Name


case class PokeAPIPokemon(name: String)

object PokeAPIPokemon {
  implicit val pokeAPIPokemonReads: Reads[PokeAPIPokemon] = (
    (__ \ "name").read[String] and
    (__ \ "url").read[String]
  )(
    (name: String, _url: String) => PokeAPIPokemon(name)
  )
}
