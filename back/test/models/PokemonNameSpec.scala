import org.scalatestplus.play._

import play.api.libs.json._

import pokezen.models.{PokemonName, PokemonNameOrdering}


class PokemonNameSpec extends PlaySpec {
  "PokemonName" should {
    "have a property name" in {
      PokemonName("foo").name mustBe "foo"
    }

    "be writable to json" in {
      Json.toJson(PokemonName("foo")).toString mustBe """"foo""""
    }

    "be writable to json when in a list" in {
      Json.toJson(
        List(
          PokemonName("foo"),
          PokemonName("bar"))).toString mustBe """["foo","bar"]"""
    }
  }
}

class PokemonNameOrderingSpec extends PlaySpec {
  "PokemonNameOrdering.compare(a, b)" should {
    "return greater than 0 if a > b" in {
      PokemonNameOrdering
        .compare(PokemonName("foo"), PokemonName("bar")) must be > 0
    }

    "return 0 if a == b" in {
      PokemonNameOrdering
        .compare(PokemonName("foo"), PokemonName("foo")) mustBe 0
    }

    "return less than 0 if a < b" in {
      PokemonNameOrdering
        .compare(PokemonName("bar"), PokemonName("foo")) must be < 0
    }
  }
}
