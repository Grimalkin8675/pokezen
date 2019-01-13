package pokezen.services.pokeapi

import play.api.libs.json._
import play.api.libs.functional.syntax._

import pokezen.{PokemonNames, PokemonName}


class PokeAPIPokemonNames(names: PokeAPIPokemonName*)
  extends PokemonNames(names: _*)

object PokeAPIPokemonNames {
  def apply(pokemons: PokeAPIPokemonName*): PokeAPIPokemonNames =
    new PokeAPIPokemonNames(pokemons: _*)

  implicit val pokeAPIPokemonNamesReads: Reads[PokeAPIPokemonNames] = (
    (__ \ "count").read[Double] and
    (__ \ "next").readNullable[String] and
    (__ \ "previous").readNullable[Boolean] and
    (__ \ "results").read[List[PokeAPIPokemonName]]
  )(
    (
      _count: Double,
      _next: Option[String],
      _previous: Option[Boolean],
      results: List[PokeAPIPokemonName]) => PokeAPIPokemonNames(results: _*)
  )
}
