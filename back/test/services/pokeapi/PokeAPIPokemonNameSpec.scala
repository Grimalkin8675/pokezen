import org.scalacheck._
import org.scalacheck.Prop._

import play.api.libs.json._

import pokezen.PokemonName
import pokezen.services.pokeapi.PokeAPIPokemonName


object PokeAPIPokemonNameSpec extends Properties("PokeAPIPokemonName") {
  property("name") = PokeAPIPokemonName("foo").name == "foo"

  property(" Parsing from json") = {
    val json = """
      {
        "name": "foo",
        "url": "some url"
      }
    """
    Json.parse(json).validate[PokeAPIPokemonName].asOpt
      .exists(_.name == "foo")
  }

  property(" Is of type PokemonName") =
    PokeAPIPokemonName("foo").isInstanceOf[PokemonName]
}
