package pokezen


case object App {
  val attack = Stat(Name("attack"), Base(45))
  val fire = Type("fire")
  val water = Type("water")
  val foo =
    Pokemon(Name("foo"), Image("foo"), Types(fire, water), Stats(attack))
  val bar = Pokemon(Name("bar"), Image("bar"), Types(fire), Stats())

  def searchPokemon(searchString: String): List[Name] = {
    if (searchString == "foo") List(
      Name("foo"),
      Name("foobar"),
      Name("bafoor")
    ) else List()
  }

  def pokemonByName(pokemonName: Name): Option[Pokemon] = {
    if (pokemonName == Name("foo")) Some(foo)
    else None
  }

  def pokemonsByType(pokemonType: Type): Set[Pokemon] = {
    if (pokemonType == fire) Set(bar, foo)
    else Set()
  }
}
