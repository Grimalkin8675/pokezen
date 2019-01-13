package pokezen


case class ComparedPokemon(pokemon: Pokemon, comparedStats: Stat*)

object ComparedPokemon {
  def compare(pokemon: Pokemon): ComparedPokemon = ComparedPokemon(pokemon)
}
