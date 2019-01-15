package pokezen


case class ComparedPokemon(pokemon: Pokemon, comparedStats: ComparedStat*)

object ComparedPokemon {
  def compare(pokemon: Pokemon, others: Pokemon*): ComparedPokemon = {
    def statToTypes(stat: Stat): Map[Type, Double] =
      pokemon.types.map((pokeType: Type) => {
        val filteredOthers = others.filter(_.hasType(pokeType))
        (pokeType, statAverage(filteredOthers, stat))
      }).toMap

    def statAverage(pokemons: Seq[Pokemon], stat: Stat): Double = {
      val statValues: Seq[Double] =
        pokemons.flatMap(_.statByName(stat.name).map(_.value))
      if (statValues.size == 0) 0
      else stat.value - statValues.fold(0d)(_ + _) / statValues.size
    }

    val comparedStats: Seq[ComparedStat] =
      pokemon.baseStats.map((stat: Stat) =>
        ComparedStat(stat.name, statToTypes(stat)))

    ComparedPokemon(pokemon, comparedStats: _*)
  }
}
