import org.scalatestplus.play._

import play.api.libs.json._

import pokezen.PokemonName
import pokezen.services.pokeapi.{PokeAPIPokemonName, PokeAPIPokemonNames}


class PokeAPIPokemonNamesSpec extends PlaySpec {
  "PokeAPIPokemonNames" should {
    "have a property names" in {
      PokeAPIPokemonNames(
        PokeAPIPokemonName("foo"),
        PokeAPIPokemonName("bar"))
      .names mustBe List(
        PokeAPIPokemonName("foo"),
        PokeAPIPokemonName("bar"))
    }

    "be parseable from a json" in {
      val json = """
        {
          "count": -124.2,
          "next": "next",
          "previous": false,
          "results": [
            {
              "name": "foo",
              "url": "some url"
            },
            {
              "name": "bar",
              "url": "other url"
            }
          ]
        }
      """
      Json.parse(json).validate[PokeAPIPokemonNames]
        .asOpt mustBe Some(
          PokeAPIPokemonNames(
            PokeAPIPokemonName("foo"),
            PokeAPIPokemonName("bar")))
    }
  }
}
