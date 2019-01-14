package pokezen


case class Pokemon(
    name: PokemonName,
    image: ImageURL,
    types: Types,
    baseStats: Stats) {
  def hasType(pokeType: Type): Boolean = this.types.contains(pokeType)

  def statByName(name: String): Option[Stat] = this.baseStats.statByName(name)
}
