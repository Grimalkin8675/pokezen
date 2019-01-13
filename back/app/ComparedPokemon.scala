package pokezen


case class ComparedPokemon(pokemon: Pokemon, comparedStats: ComparedStat*)

object ComparedPokemon {
  def compare(pokemon: Pokemon): ComparedPokemon = ComparedPokemon(pokemon)
}
