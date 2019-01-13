package tests

import scala.concurrent._

import pokezen.{PokemonNames, PokemonName}
import pokezen.controllers.SearcheableService


case class MockSearchService() extends SearcheableService {
  def pokemons: Future[PokemonNames] =
    Future {
      PokemonNames(PokemonName("foo"), PokemonName("bar"))
    }(ExecutionContext.global)
}
