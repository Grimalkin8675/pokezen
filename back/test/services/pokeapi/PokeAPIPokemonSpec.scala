import org.scalatestplus.play._

import play.api.libs.json._

import pokezen._
import pokezen.services.pokeapi.PokeAPIPokemon


class PokeAPIPokemonSpec extends PlaySpec {
  "PokeAPIPokemon" should {
    "be instance of Pokemon" in {
      PokeAPIPokemon(PokemonName(""), ImageURL(""), Types(), Stats())
        .isInstanceOf[Pokemon] mustBe true
    }

    "be parseable from json" in {
      val json = """
        {
          "abilities": [],
          "base_experience": -1,
          "forms": [],
          "game_indices": [],
          "name": "foo",
          "sprites": {
            "front_default": "foo image url",
            "back_default": "not used field"
          },
          "types": [
            {
              "slot": 2,
              "type": {
                "name": "poison",
                "url": "some url"
              }
            },
            {
              "type": {
                "name": "grass"
              }
            }
          ],
          "stats": [
            {
              "base_stat": 45,
              "effort": 0,
              "stat": {
                "name": "speed",
                "url": "other url"
              }
            },
            {
              "base_stat": 65,
              "stat": {
                "name": "defense"
              }
            }
          ]
        }
      """
      Json.parse(json).validate[PokeAPIPokemon]
          .asOpt mustBe Some(
            Pokemon(
              PokemonName("foo"),
              ImageURL("foo image url"),
              Types(Type("poison"), Type("grass")),
              Stats(Stat("speed", 45), Stat("defense", 65))))
    }
  }
}
