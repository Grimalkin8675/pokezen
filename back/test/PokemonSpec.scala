import org.scalatestplus.play._

import pokezen.{Pokemon, PokemonName, ImageURL, Types, Type, Stats, Stat}


class PokemonSpec extends PlaySpec {
  "Pokemon" should {
    "have a name property" in {
      Pokemon(
        PokemonName("foo"),
        ImageURL(""),
        Types(),
        Stats()).name mustBe PokemonName("foo")
    }

    "have a image property" in {
      Pokemon(
        PokemonName(""),
        ImageURL("image_url"),
        Types(),
        Stats()).image mustBe ImageURL("image_url")
    }

    "have a types property" in {
      Pokemon(
        PokemonName(""),
        ImageURL(""),
        Types(Type("fire"), Type("air")),
        Stats()).types mustBe Types(Type("fire"), Type("air"))
    }

    "have a baseStats property" in {
      Pokemon(
        PokemonName(""),
        ImageURL(""),
        Types(),
        Stats(Stat("speed", 70), Stat("defense", 50)))
      .baseStats mustBe Stats(Stat("speed", 70), Stat("defense", 50))
    }
  }

  "Pokemon.hasType(pokeType)" should {
    "return true if pokemon has type" in {
      Pokemon(
        PokemonName(""),
        ImageURL(""),
        Types(Type("fire"), Type("air")),
        Stats()).hasType(Type("air")) mustBe true
    }

    "return true if pokemon hasn't type" in {
      Pokemon(
        PokemonName(""),
        ImageURL(""),
        Types(Type("fire"), Type("air")),
        Stats()).hasType(Type("water")) mustBe false
    }
  }
}
