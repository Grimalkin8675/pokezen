import org.scalatestplus.play._

import scala.concurrent._
import scala.concurrent.duration._
import play.api.mvc._
import play.api.test.FakeRequest
import play.api.test.Helpers.{stubControllerComponents, contentAsString}
import play.api.libs.json._

import pokezen.controllers.PokemonDetails


class PokemonDetailsSpec extends PlaySpec {
  "PokemonDetails.pokemon(pokemonName)" should {
    "return correctly formated json" in {
      val controller: PokemonDetails =
        PokemonDetails(
          stubControllerComponents(),
          ExecutionContext.global)
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
              "base": 50
            }
          ],
          "stats": [
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
