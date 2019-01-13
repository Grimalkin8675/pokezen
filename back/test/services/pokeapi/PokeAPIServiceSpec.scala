import org.scalacheck._
import org.scalacheck.Prop._

import scala.concurrent._
import scala.concurrent.duration._
import play.api.mvc._
import play.api.mvc.Results.Ok
import play.api.libs.json._
import play.api.test._
import play.api.test.Helpers._
import play.core.server.Server
import mockws.MockWS

import pokezen.PokemonName
import pokezen.services.pokeapi.PokeAPIService


object PokeAPIServiceSpec extends Properties("PokeAPIService") {
  property("pokemons() should return pokemon names") = {
    val json = """
      {
        "count": 455,
        "next": null,
        "previous": null,
        "results": [
          {
            "name": "foo",
            "url": "some url"
          },
          {
            "name": "bar",
            "url": "other url"
          },
          {
            "name": "bafooba",
            "url": "another url"
          }
        ]
      }
    """
    val ws = MockWS {
      case (GET, "https://pokeapi.co/api/v2/pokemon") => Action {
        Ok(Json.parse(json))
      }
    }
    val futureResult =
      PokeAPIService(ws, ExecutionContext.global).pokemons()
    val res = Await.result(futureResult, 1 seconds)
    (   res.size == 3
    &&  res.contains(PokemonName("foo"))
    &&  res.contains(PokemonName("bar"))
    &&  res.contains(PokemonName("bafooba")))
  }

  property("pokemons() should return sorted pokemon names") = {
    val json = """
      {
        "count": 455,
        "next": null,
        "previous": null,
        "results": [
          {
            "name": "ghi",
            "url": "some url"
          },
          {
            "name": "abcd",
            "url": "other url"
          },
          {
            "name": "abc",
            "url": "other url"
          },
          {
            "name": "def",
            "url": "another url"
          }
        ]
      }
    """
    val ws = MockWS {
      case (GET, "https://pokeapi.co/api/v2/pokemon") => Action {
        Ok(Json.parse(json))
      }
    }
    val futureResult =
      PokeAPIService(ws, ExecutionContext.global).pokemons()
    Await.result(futureResult, 1 seconds) == List(
      PokemonName("abc"),
      PokemonName("abcd"),
      PokemonName("def"),
      PokemonName("ghi")
    )
  }
}
