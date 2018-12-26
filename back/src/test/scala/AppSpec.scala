import org.scalacheck._
import org.scalacheck.Prop._

import pokezen._


object AppSpec extends Properties("App") {
  val attack = Stat(Name("attack"), Base(45))
  val fire = Type("fire")
  val water = Type("water")
  val foo =
    Pokemon(Name("foo"), Image("foo"), Types(fire, water), Stats(attack))
  val bar = Pokemon(Name("bar"), Image("bar"), Types(fire), Stats())

  property("searchPokemon(searchString) should return names sorted by relevance if match found") =
    App.searchPokemon("foo") == List(
      Name("foo"),
      Name("foobar"),
      Name("bafoor")
    )

  property("searchPokemon(searchString) should return empty list if no match found") =
    App.searchPokemon("bar") == List()

  property("pokemonByName(name) should return Some(pokemon) if it exists") =
    App.pokemonByName(Name("foo")) == Some(foo)

  property("pokemonByName(name) should return None if it doesn't exist") =
    App.pokemonByName(Name("bar")) == None

  property("pokemonsByType(type) should return all pokemons having the type type") = {
    App.pokemonsByType(fire) == Set(foo, bar)
  }
}
