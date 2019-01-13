package pokezen.services.pokeapi

import pokezen.{Types, Type}


class PokeAPITypes(types: Type*) extends Types(types: _*)

object PokeAPITypes {
  def apply(types: Type*): PokeAPITypes = new PokeAPITypes(types: _*)
}
