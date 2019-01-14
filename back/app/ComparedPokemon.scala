package pokezen


case class ComparedPokemon(pokemon: Pokemon, comparedStats: ComparedStat*)

object ComparedPokemon {
  def compare(pokemon: Pokemon): ComparedPokemon = {
    def statToTypes(pokemon: Pokemon, stat: Stat): Map[Type, Double] =
      pokemon.types.map((_, 0d)).toMap

    val comparedStats: Seq[ComparedStat] =
      pokemon.baseStats.map((stat: Stat) =>
        ComparedStat(stat.name, statToTypes(pokemon, stat)))

    ComparedPokemon(pokemon, comparedStats: _*)
  }
}
