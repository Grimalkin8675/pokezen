package pokezen.services.pokeapi

import play.api.libs.json._
import play.api.libs.functional.syntax._

import pokezen.Name


case class PokeAPIPokemons(pokemons: List[PokeAPIPokemon])
