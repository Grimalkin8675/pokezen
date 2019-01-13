import org.scalacheck._
import org.scalacheck.Prop._

import pokezen.{Pokemon, PokemonName}


object PokemonSpec extends Properties("Pokemon") {
  property("name") = Pokemon("foo").name == PokemonName("foo")
}
