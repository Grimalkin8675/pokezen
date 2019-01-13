import org.scalatestplus.play._

import scala.concurrent._
import scala.concurrent.duration._
import play.api.mvc._
import play.api.test.FakeRequest
import play.api.test.Helpers.{stubControllerComponents, contentAsString}

import tests.MockSearchService
import pokezen.controllers.{SearcheableService, SearchPokemon}


class SearchPokemonSpec extends PlaySpec {
  "SearchPokemon.searchPokemon(searchString)" should {
    "return correct json" in {
      val controller: SearchPokemon =
        SearchPokemon(
          MockSearchService(),
          stubControllerComponents(),
          ExecutionContext.global)
      val result: Future[Result] = controller.pokemons.apply(FakeRequest())
      val bodyText: String = contentAsString(result)(1 seconds)
      bodyText mustBe """["foo","bar"]"""
    }
  }
}
