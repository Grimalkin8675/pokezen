import org.scalacheck.Properties

import scala.concurrent._
import scala.concurrent.duration._
import play.api.mvc._
import play.api.test.FakeRequest
import play.api.test.Helpers.{stubControllerComponents, contentAsString}

import tests.MockSearchService
import pokezen.controllers.{SearcheableService, SearchPokemon}


object SearchPokemonSpec extends Properties("SearchPokemon") {
  property("searchPokemon") = {
    val controller: SearchPokemon =
      SearchPokemon(
        MockSearchService(),
        stubControllerComponents(),
        ExecutionContext.global)
    val result: Future[Result] = controller.pokemons.apply(FakeRequest())
    val bodyText: String = contentAsString(result)(1 seconds)
    bodyText == """["foo","bar"]"""
  }
}
