package pokezen


case class Pokemon(
    name: PokemonName,
    image: Option[ImageURL],
    types: Types,
    baseStats: Stats) {
  def hasType(pokeType: Type): Boolean = this.types.contains(pokeType)

  def hasStat(statName: String): Boolean = this.baseStats.contains(statName)

  def statByName(name: String): Option[Stat] = this.baseStats.statByName(name)
}
