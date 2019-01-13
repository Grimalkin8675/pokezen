package pokezen


case class ComparedPokemon(pokemon: Pokemon, comparedStats: Stats)

object ComparedPokemon {
  def compare(pokemon: Pokemon): ComparedPokemon =
    ComparedPokemon(pokemon, pokemon.baseStats)
}
