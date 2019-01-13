import org.scalatestplus.play._

import play.api.libs.json._

import pokezen.{PokemonNames, PokemonName}


class PokemonNamesSpec extends PlaySpec {
  "PokemonNames" should {
    "have property name" in {
      PokemonNames(
        PokemonName("foo"),
        PokemonName("bar"))
      .names mustBe List(
        PokemonName("foo"),
        PokemonName("bar"))
    }

    "be writable to json" in {
      Json.toJson(
        PokemonNames(
          PokemonName("foo"),
          PokemonName("bar"))).toString mustBe """["foo","bar"]"""
    }
  }
}
