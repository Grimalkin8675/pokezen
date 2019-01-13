package tests

import scala.concurrent._

import pokezen.PokemonName
import pokezen.controllers.SearcheableService


case class MockSearchService() extends SearcheableService {
  def pokemons(): Future[List[PokemonName]] =
    Future {
      List(PokemonName("foo"), PokemonName("bar"))
    }(ExecutionContext.global)
}
