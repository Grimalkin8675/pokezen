package pokezen.services.pokeapi

import play.api.libs.json._
import play.api.libs.functional.syntax._

import pokezen.Name


case class PokeAPIPokemonName(name: String) {
  def toName: Name = Name(this.name)
}

object PokeAPIPokemonName {
  implicit val pokeAPIPokemonNameReads: Reads[PokeAPIPokemonName] = (
    (__ \ "name").read[String] and
    (__ \ "url").read[String]
  )(
    (name: String, _url: String) => PokeAPIPokemonName(name)
  )
}
