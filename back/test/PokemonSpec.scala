import org.scalacheck._
import org.scalacheck.Prop._

import pokezen.{Pokemon, PokemonName, ImageURL, Types, Type, Stats, Stat}


object PokemonSpec extends Properties("Pokemon") {
  property("name") =
    Pokemon(PokemonName("foo"), ImageURL(""), Types(), Stats())
    .name == PokemonName("foo")

  property("image") =
    Pokemon(PokemonName(""), ImageURL("image_url"), Types(), Stats())
    .image == ImageURL("image_url")

  property("types") =
    Pokemon(
      PokemonName(""),
      ImageURL(""),
      Types(Type("fire"),
      Type("air")),
      Stats())
    .types == Types(Type("fire"), Type("air"))

  property("baseStats") =
    Pokemon(
      PokemonName(""),
      ImageURL(""),
      Types(),
      Stats(Stat("speed", 70), Stat("defense", 50)))
    .baseStats == Stats(Stat("speed", 70), Stat("defense", 50))
}
