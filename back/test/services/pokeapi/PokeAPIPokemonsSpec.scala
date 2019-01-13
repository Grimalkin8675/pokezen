import org.scalacheck._
import org.scalacheck.Prop._

import play.api.libs.json._

import pokezen.PokemonName
import pokezen.services.pokeapi.{PokeAPIPokemonName, PokeAPIPokemonNames}


object PokeAPIPokemonNamesSpec extends Properties("PokeAPIPokemonNames") {
  property("names") =
    PokeAPIPokemonNames(PokeAPIPokemonName("foo"), PokeAPIPokemonName("bar"))
    .names == List(PokeAPIPokemonName("foo"), PokeAPIPokemonName("bar"))

  property(" Parsing from json") = {
    val json = """
      {
        "count": -124.2,
        "next": "next",
        "previous": false,
        "results": [
          {
            "name": "foo",
            "url": "some url"
          },
          {
            "name": "bar",
            "url": "other url"
          }
        ]
      }
    """
    Json.parse(json).validate[PokeAPIPokemonNames].asOpt
      .exists(_.names == List(
        PokeAPIPokemonName("foo"), PokeAPIPokemonName("bar")))
  }
}
