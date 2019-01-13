import org.scalacheck._
import org.scalacheck.Prop._

import pokezen.{Pokemon, PokemonName, ImageURL, Types, Type}


object PokemonSpec extends Properties("Pokemon") {
  property("name") =
    Pokemon(PokemonName("foo"), ImageURL(""), Types())
    .name == PokemonName("foo")

  property("image") =
    Pokemon(PokemonName(""), ImageURL("image_url"), Types())
    .image == ImageURL("image_url")

  property("types") =
    Pokemon(PokemonName(""), ImageURL(""), Types(Type("fire"), Type("air")))
    .types == Types(Type("fire"), Type("air"))
}
