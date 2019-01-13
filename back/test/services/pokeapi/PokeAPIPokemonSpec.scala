import org.scalacheck._
import org.scalacheck.Prop._

import play.api.libs.json._

import pokezen.{Pokemon, PokemonName, ImageURL, Types, Stats}
import pokezen.services.pokeapi.PokeAPIPokemon


object PokeAPIPokemonSpec extends Properties("PokeAPIPokemon") {
  property(" Is of type Pokemon") =
    PokeAPIPokemon(PokemonName(""), ImageURL(""), Types(), Stats())
      .isInstanceOf[Pokemon]
}
