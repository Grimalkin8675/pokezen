import org.scalacheck._
import org.scalacheck.Prop._

import play.api.libs.json._

import pokezen.{PokemonNames, PokemonName}


object PokemonNamesSpec extends Properties("PokemonNames") {
  property("name") =
    PokemonNames(PokemonName("foo"), PokemonName("bar"))
    .names == List(PokemonName("foo"), PokemonName("bar"))

  property(" stringifying list") = {
    val res = PokemonNames(PokemonName("foo"), PokemonName("bar"))
    Json.toJson(res).toString == """["foo","bar"]"""
  }
}
