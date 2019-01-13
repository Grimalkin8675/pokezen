package pokezen


class Pokemon(_name: PokemonName) {
  def name = this._name
}

object Pokemon {
  def apply(name: String) = new Pokemon(PokemonName(name))
}
