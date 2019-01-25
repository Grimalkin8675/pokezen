package pokezen.models


case class Pokemon(
  name: PokemonName,
  image: Option[ImageURL],
  types: Types,
  baseStats: Stats) {

  def hasType(pokeType: Type): Boolean = types.contains(pokeType)

  def hasStat(statName: String): Boolean = baseStats.contains(statName)

  def statByName(name: String): Option[Stat] = baseStats.statByName(name)
}
