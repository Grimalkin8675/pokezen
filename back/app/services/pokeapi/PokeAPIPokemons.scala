package pokezen.services.pokeapi

import play.api.libs.json._
import play.api.libs.functional.syntax._

import pokezen.{PokemonNames, PokemonName}


class PokeAPIPokemons(names: PokeAPIPokemonName*) extends PokemonNames(names: _*)

object PokeAPIPokemons {
  def apply(pokemons: PokeAPIPokemonName*): PokeAPIPokemons =
    new PokeAPIPokemons(pokemons: _*)

  implicit val pokeAPIPokemonsReads: Reads[PokeAPIPokemons] = (
    (__ \ "count").read[Double] and
    (__ \ "next").readNullable[String] and
    (__ \ "previous").readNullable[Boolean] and
    (__ \ "results").read[List[PokeAPIPokemonName]]
  )(
    (
      _count: Double,
      _next: Option[String],
      _previous: Option[Boolean],
      results: List[PokeAPIPokemonName]) => PokeAPIPokemons(results: _*)
  )
}
