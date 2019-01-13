import org.scalacheck._
import org.scalacheck.Prop._

import pokezen.{Pokemon, PokemonName, ImageURL}


object PokemonSpec extends Properties("Pokemon") {
  property("name") =
    Pokemon(PokemonName("foo"), ImageURL("")).name == PokemonName("foo")

  property("image") =
    Pokemon(PokemonName(""), ImageURL("image_url")).image ==
      ImageURL("image_url")
}
