package pokezen


case class Pokemon(
    name: PokemonName,
    image: ImageURL,
    types: Types,
    baseStats: Stats) {
  def hasType(pokeType: Type): Boolean = this.types.contains(pokeType)
}
