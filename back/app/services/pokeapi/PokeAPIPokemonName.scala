package pokezen.services.pokeapi

import play.api.libs.json._
import play.api.libs.functional.syntax._

import pokezen.PokemonName


case class PokeAPIPokemonName(name: String) {
  def toName: PokemonName = PokemonName(this.name)
}

object PokeAPIPokemonName {
  implicit val pokeAPIPokemonNameReads: Reads[PokeAPIPokemonName] = (
    (__ \ "name").read[String] and
    (__ \ "url").read[String]
  )(
    (name: String, _url: String) => PokeAPIPokemonName(name)
  )
}
