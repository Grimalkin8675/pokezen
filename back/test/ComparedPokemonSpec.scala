import org.scalatestplus.play._

import play.api.libs.json._

import pokezen._


class ComparedPokemonSpec extends PlaySpec {
  val fooPokemon =
    Pokemon(
      PokemonName("foo"),
      ImageURL("some url"),
      Types(Type("fire"), Type("water")),
      Stats(Stat("defense", 100), Stat("attack", 50)))

  "ComparedPokemon" should {
    "have an attribute pokemon" in {
      ComparedPokemon(fooPokemon).pokemon mustBe fooPokemon
    }

    "have an attribute comparedStats" in {
      ComparedPokemon(
          fooPokemon,
          ComparedStat(
            "att",
            Map(
              Type("earth") -> -5,
              Type("wind") -> 2)),
          ComparedStat(
            "def",
            Map(
              Type("poison") -> 3)))
        .comparedStats mustBe List(
          ComparedStat(
            "att",
            Map(
              Type("earth") -> -5,
              Type("wind") -> 2)),
          ComparedStat(
            "def",
            Map(
              Type("poison") -> 3)))
    }

    "parse to json" in {
      Json.toJson(
        ComparedPokemon(
            fooPokemon,
            ComparedStat(
              "att",
              Map(
                Type("earth") -> -5,
                Type("wind") -> 2)),
            ComparedStat(
              "def",
              Map(
                Type("poison") -> 3)))) mustBe Json.parse("""
        {
          "name": "foo",
          "image": "some url",
          "types": ["fire", "water"],
          "base_stats": [
            {
              "name": "defense",
              "value": 100
            },
            {
              "name": "attack",
              "value": 50
            }
          ],
          "compared_stats": [
            {
              "name": "att",
              "comparisons": {
                "earth": -5,
                "wind": 2
              }
            },
            {
              "name": "def",
              "comparisons": {
                "poison": 3
              }
            }
          ]
        }
      """)
    }
  }

  "ComparedPokemon.compare(pokemon, otherPokemons)" should {
    "create a new ComparedPokemon" in {
      ComparedPokemon.compare(fooPokemon)
        .isInstanceOf[ComparedPokemon] mustBe true
    }

    "compare pokemon to itself if no other pokemons given" in {
      ComparedPokemon.compare(fooPokemon)
        .comparedStats mustBe List(
          ComparedStat(
            "defense",
            Map(
              Type("fire") -> 0,
              Type("water") -> 0)),
          ComparedStat(
            "attack",
            Map(
              Type("fire") -> 0,
              Type("water") -> 0)))
    }

    "compare pokemon to other pokemons' stats having the same type" in {
      ComparedPokemon.compare(
        fooPokemon,
        Pokemon(
          PokemonName(""),
          ImageURL(""),
          Types(Type("fire")),
          Stats(Stat("defense", 70))),
        Pokemon(
          PokemonName(""),
          ImageURL(""),
          Types(Type("fire")),
          Stats(Stat("defense", 90)))
      ).comparedStats mustBe List(
          ComparedStat(
            "defense",
            Map(
              Type("fire") -> 20,
              Type("water") -> 0)),
          ComparedStat(
            "attack",
            Map(
              Type("fire") -> 0,
              Type("water") -> 0)))
    }

    "not compare pokemon to others if other pokemons don't have same type" in {
      ComparedPokemon.compare(
        fooPokemon,
        Pokemon(
          PokemonName(""),
          ImageURL(""),
          Types(Type("earth")),
          Stats(Stat("defense", 70)))
      ).comparedStats mustBe List(
          ComparedStat(
            "defense",
            Map(
              Type("fire") -> 0,
              Type("water") -> 0)),
          ComparedStat(
            "attack",
            Map(
              Type("fire") -> 0,
              Type("water") -> 0)))
    }
  }
}
