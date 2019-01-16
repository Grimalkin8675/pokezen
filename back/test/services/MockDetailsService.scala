package tests

import scala.concurrent._

import pokezen._
import pokezen.controllers.DetaileableService


case class MockDetailsService() extends DetaileableService {
  implicit val ec = ExecutionContext.global

  val foo = Pokemon(PokemonName("whatever"), None, Types(), Stats())

  val bar = Pokemon(
    PokemonName("bar"),
    Some(ImageURL("bar_image")),
    Types(Type("fire"), Type("air")),
    Stats(Stat("speed", 70), Stat("defense", 50)))

  val otherFirePokemon = Pokemon(
    PokemonName("otherFirePokemon"),
    None,
    Types(Type("fire")),
    Stats(Stat("speed", 60), Stat("defense", 80)))

  val otherAirPokemon = Pokemon(
    PokemonName("otherAirPokemon"),
    None,
    Types(Type("air")),
    Stats(Stat("speed", 90), Stat("defense", 30)))

  def pokemonByName(name: PokemonName): Future[Option[Pokemon]] = Future {
    name match {
      case PokemonName("foo") => Some(foo)
      case PokemonName("bar") => Some(bar)
      case PokemonName("otherFirePokemon") => Some(otherFirePokemon)
      case PokemonName("otherAirPokemon") => Some(otherAirPokemon)
      case _ => None
    }
  }

  def pokemonsOfType(pokemonType: Type): Future[Option[PokemonNames]] = Future {
    pokemonType match {
      case Type("fire") => Some(PokemonNames(bar.name, otherFirePokemon.name))
      case Type("air") => Some(PokemonNames(bar.name, otherAirPokemon.name))
      case _ => None
    }
  }
}
