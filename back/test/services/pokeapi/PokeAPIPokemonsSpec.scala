import org.scalacheck._
import org.scalacheck.Prop._

import pokezen.services.pokeapi.{PokeAPIPokemon, PokeAPIPokemons}


object PokeAPIPokemonsSpec extends Properties("PokeAPIPokemons") {
  property("pokemons") = {
    val pokemons: List[PokeAPIPokemon] =
      List(PokeAPIPokemon("foo"), PokeAPIPokemon("bar"))
    PokeAPIPokemons(pokemons).pokemons == pokemons
  }
}
