package pokezen.services.pokeapi

import play.api.libs.json._
import play.api.libs.functional.syntax._

import pokezen.{Pokemon, PokemonName, ImageURL, Types, Stats}


class PokeAPIPokemon(
  name: PokemonName,
  image: ImageURL,
  types: Types,
  baseStats: Stats) extends Pokemon(name, image, types, baseStats)

object PokeAPIPokemon {
  def apply(
      name: PokemonName,
      image: ImageURL,
      types: Types,
      baseStats: Stats): PokeAPIPokemon =
    new PokeAPIPokemon(name, image, types, baseStats)
}
