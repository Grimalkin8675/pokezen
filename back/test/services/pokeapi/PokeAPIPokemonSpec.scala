import org.scalacheck._
import org.scalacheck.Prop._

import play.api.libs.json._

import pokezen.Name
import pokezen.services.pokeapi.PokeAPIPokemon


object PokeAPIPokemonSpec extends Properties("PokeAPIPokemon") {
  property("name") = PokeAPIPokemon("foo").name == "foo"

  property(" Parsing from json") = {
    val json = """
      {
        "name": "foo",
        "url": "some url"
      }
    """
    Json.parse(json).validate[PokeAPIPokemon].asOpt
      .exists(_.name == "foo")
  }

  property("toName") = PokeAPIPokemon("foo").toName == Name("foo")
}
