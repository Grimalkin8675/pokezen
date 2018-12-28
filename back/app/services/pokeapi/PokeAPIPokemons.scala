package pokezen.services.pokeapi

import play.api.libs.json._
import play.api.libs.functional.syntax._

import pokezen.Name


case class PokeAPIPokemons(pokemons: List[PokeAPIPokemon])

object PokeAPIPokemons {
  implicit val pokeAPIPokemonsReads: Reads[PokeAPIPokemons] = (
    (__ \ "count").read[Double] and
    (__ \ "next").readNullable[String] and
    (__ \ "previous").readNullable[Boolean] and
    (__ \ "results").read[List[PokeAPIPokemon]]
  )(
    (
      _count: Double,
      _next: Option[String],
      _previous: Option[Boolean],
      results: List[PokeAPIPokemon]) => PokeAPIPokemons(results)
  )
}
