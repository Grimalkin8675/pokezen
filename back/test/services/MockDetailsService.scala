package tests

import scala.concurrent._

import pokezen._
import pokezen.controllers.DetaileableService


case class MockDetailsService() extends DetaileableService {
  implicit val ec = ExecutionContext.global

  val foo = Pokemon(PokemonName("whatever"), ImageURL(""), Types(), Stats())

  val bar = Pokemon(
    PokemonName("bar"),
    ImageURL("bar_image"),
    Types(Type("fire"), Type("air")),
    Stats(Stat("speed", 70), Stat("defense", 50)))

  val otherFirePokemon = Pokemon(
    PokemonName("otherFirePokemon"),
    ImageURL(""),
    Types(Type("fire")),
    Stats(Stat("speed", 60), Stat("defense", 80)))

  val otherAirPokemon = Pokemon(
    PokemonName("otherAirPokemon"),
    ImageURL(""),
    Types(Type("air")),
    Stats(Stat("speed", 90), Stat("defense", 30)))

  def pokemonByName(name: PokemonName): Future[Pokemon] = Future {
    name match {
      case PokemonName("foo") => foo
      case PokemonName("bar") => bar
      case PokemonName("otherFirePokemon") => otherFirePokemon
      case PokemonName("otherAirPokemon") => otherAirPokemon
      case _ => throw new Exception(s"pokemon not found: ${name}")
    }
  }

  def pokemonsOfType(pokemonType: Type): Future[PokemonNames] = Future {
    pokemonType match {
      case Type("fire") => PokemonNames(bar.name, otherFirePokemon.name)
      case Type("air") => PokemonNames(bar.name, otherAirPokemon.name)
      case _ => throw new Exception(s"pokemon type not found: ${pokemonType}")
    }
  }
}
