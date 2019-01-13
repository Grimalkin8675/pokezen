import org.scalatestplus.play._

import play.api.libs.json._

import pokezen.PokemonName
import pokezen.services.pokeapi.PokeAPIPokemonName


class PokeAPIPokemonNameSpec extends PlaySpec {
  "PokeAPIPokemonName" should {
    "have a name property" in {
      PokeAPIPokemonName("foo").name mustBe "foo"
    }

    "be an instance of PokemonName" in {
      PokeAPIPokemonName("foo").isInstanceOf[PokemonName] mustBe true
    }

    "be parseable from a json" in {
      val json = """
        {
          "name": "foo",
          "url": "some url"
        }
      """
      Json.parse(json).validate[PokeAPIPokemonName]
          .asOpt mustBe Some(PokeAPIPokemonName("foo"))
    }
  }
}
