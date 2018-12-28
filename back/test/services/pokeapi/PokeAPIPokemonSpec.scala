import org.scalacheck._
import org.scalacheck.Prop._

import pokezen.services.pokeapi.PokeAPIPokemon


object PokeAPIPokemonSpec extends Properties("PokeAPIPokemon") {
  property("name") = PokeAPIPokemon("foo").name == "foo"
}
