import org.scalacheck._
import org.scalacheck.Prop._

import play.api.libs.json._

import pokezen.PokemonName
import pokezen.services.pokeapi.{PokeAPIPokemonName, PokeAPIPokemons}


object PokeAPIPokemonsSpec extends Properties("PokeAPIPokemons") {
  property("pokemons") = {
    val pokemons: List[PokeAPIPokemonName] =
      List(PokeAPIPokemonName("foo"), PokeAPIPokemonName("bar"))
    PokeAPIPokemons(pokemons).pokemons == pokemons
  }

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
    Json.parse(json).validate[PokeAPIPokemons].asOpt
      .exists(_.pokemons == List(
        PokeAPIPokemonName("foo"), PokeAPIPokemonName("bar")))
  }

  property("toNames") =
    PokeAPIPokemons(List(PokeAPIPokemonName("foo"), PokeAPIPokemonName("bar")))
      .toNames == List(PokemonName("foo"), PokemonName("bar"))
}
