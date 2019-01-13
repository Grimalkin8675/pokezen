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
      ComparedPokemon(fooPokemon, Stats()).pokemon mustBe fooPokemon
    }

    "have an attribute comparedStats" in {
      ComparedPokemon(fooPokemon, Stats(Stat("att", -5), Stat("def", 10)))
        .comparedStats mustBe Stats(Stat("att", -5), Stat("def", 10))
    }
  }

  "ComparedPokemon.compare(pokemon, otherPokemons)" should {
    "create a new ComparedPokemon" in {
      ComparedPokemon.compare(fooPokemon)
        .isInstanceOf[ComparedPokemon] mustBe true
    }

    "compare pokemon to itself if no other pokemons given" in {
      ComparedPokemon.compare(fooPokemon)
        .comparedStats mustBe fooPokemon.baseStats
    }
  }
}
