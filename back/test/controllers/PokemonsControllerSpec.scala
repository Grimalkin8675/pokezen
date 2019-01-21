import org.scalatestplus.play._

import scala.concurrent._
import scala.concurrent.duration._
import play.api.mvc._
import play.api.test.FakeRequest
import play.api.test.Helpers.{stubControllerComponents, contentAsString}
import play.api.libs.json._

import tests.MockPokemonsService
import pokezen.controllers.PokemonsController


class PokemonsControllerSpec extends PlaySpec {
  implicit val ec = ExecutionContext.global

  "PokemonsController.searchPokemon(searchString)" should {
    "return correct json" in {
      val controller: PokemonsController =
        PokemonsController(
          MockPokemonsService(),
          stubControllerComponents())
      val result: Future[Result] = controller.pokemons.apply(FakeRequest())
      val bodyText: String = contentAsString(result)(1 seconds)
      bodyText mustBe """["foo","bar"]"""
    }
  }

  "PokemonsController.pokemon(pokemonName)" should {
    "return correctly formated json" in {
      val controller: PokemonsController =
        PokemonsController(
          MockPokemonsService(),
          stubControllerComponents())
      val result: Future[Result] =
        controller.pokemon("bar").apply(FakeRequest())
      val bodyText: String = contentAsString(result)(1 seconds)
      val res = Json.parse("""
        {
          "name": "bar",
          "image": "bar_image",
          "types": ["fire", "air"],
          "base_stats": [
            {
              "name": "speed",
              "value": 70
            },
            {
              "name": "defense",
              "value": 50
            }
          ],
          "compared_stats": [
            {
              "name": "speed",
              "comparisons": {
                "fire": 5,
                "air": -10
              }
            },
            {
              "name": "defense",
              "comparisons": {
                "fire": -15,
                "air": 10
              }
            }
          ]
        }
      """)
      bodyText mustBe res.toString
    }
  }
}
