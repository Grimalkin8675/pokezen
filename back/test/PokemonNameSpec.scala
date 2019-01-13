import org.scalacheck._
import org.scalacheck.Prop._

import play.api.libs.json._

import pokezen.{PokemonName, PokemonNameOrdering}


object PokemonNameSpec extends Properties("PokemonName") {
  property("name") = PokemonName("foo").name == "foo"

  property(" stringifying") =
    Json.toJson(PokemonName("foo")).toString == """"foo""""

  property(" stringifying list") =
    Json.toJson(List(PokemonName("foo"), PokemonName("bar"))).toString ==
      """["foo","bar"]"""
}

object PokemonNameOrderingSpec extends Properties("PokemonNameOrdering") {
  property("compare(a, b)") =
    PokemonNameOrdering.compare(PokemonName("foo"), PokemonName("bar")) > 0

  property("compare(a, b)") =
    PokemonNameOrdering.compare(PokemonName("foo"), PokemonName("foo")) == 0

  property("compare(a, b)") =
    PokemonNameOrdering.compare(PokemonName("bar"), PokemonName("foo")) < 0
}
