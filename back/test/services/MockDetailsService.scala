package tests

import scala.concurrent._

import pokezen.{Pokemon, PokemonNames, PokemonName, Type}
import pokezen.controllers.DetaileableService


case class MockDetailsService() extends DetaileableService {
  def pokemonByName(name: PokemonName): Future[Pokemon] = ???
  def pokemonsOfType(pokemonType: Type): Future[PokemonNames] = ???
}
