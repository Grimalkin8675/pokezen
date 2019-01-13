import org.scalatestplus.play._

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
  }
}
