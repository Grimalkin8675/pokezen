import org.scalacheck._
import org.scalacheck.Prop._
import org.scalatestplus.play._

import scala.concurrent._
import scala.concurrent.duration._
import play.api.mvc._
import play.api.mvc.Results.Ok
import play.api.libs.json._
import play.api.test._
import play.api.test.Helpers._
import play.core.server.Server
import mockws.MockWS

import pokezen._
import pokezen.services.pokeapi.PokeAPIService


object PokeAPIServiceSpec extends Properties("PokeAPIService") {
  property("pokemons should return pokemon names") = {
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
    val futureResult = PokeAPIService(ws, ExecutionContext.global).pokemons
    val res = Await.result(futureResult, 1 seconds)
    (   res.names.size == 3
    &&  res.names.contains(PokemonName("foo"))
    &&  res.names.contains(PokemonName("bar"))
    &&  res.names.contains(PokemonName("bafooba")))
  }

  property("pokemons should return sorted pokemon names") = {
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
    val futureResult = PokeAPIService(ws, ExecutionContext.global).pokemons
    Await.result(futureResult, 1 seconds) == PokemonNames(
      PokemonName("abc"),
      PokemonName("abcd"),
      PokemonName("def"),
      PokemonName("ghi")
    )
  }

  property("pokemonByName(name) should return a Pokemon") = {
    val json = """
      {
        "abilities": [],
        "base_experience": -1,
        "forms": [],
        "game_indices": [],
        "name": "foo",
        "sprites": {
          "front_default": "foo image url",
          "back_default": "not used field"
        },
        "types": [
          {
            "slot": 2,
            "type": {
              "name": "poison",
              "url": "some url"
            }
          },
          {
            "type": {
              "name": "grass"
            }
          }
        ],
        "stats": [
          {
            "base_stat": 45,
            "effort": 0,
            "stat": {
              "name": "speed",
              "url": "other url"
            }
          },
          {
            "base_stat": 65,
            "stat": {
              "name": "defense"
            }
          }
        ]
      }
    """
    val ws = MockWS {
      case (GET, "https://pokeapi.co/api/v2/pokemon/some-pokemon-name") =>
        Action {
          Ok(Json.parse(json))
        }
    }
    val futureResult = PokeAPIService(ws, ExecutionContext.global)
      .pokemonByName(PokemonName("some-pokemon-name"))
    Await.result(futureResult, 1 seconds) ==
      Pokemon(
        PokemonName("foo"),
        ImageURL("foo image url"),
        Types(Type("poison"), Type("grass")),
        Stats(Stat("speed", 45), Stat("defense", 65)))
  }
}


class PokeAPIServiceSpec2 extends PlaySpec {
  "pokemonsOfType(type)" must {
    "return all pokemons' names of a certain type" in {
      val json = """
        {
          "names": [],
          "pokemon": [
            {
              "pokemon": {
                "name": "foo",
                "url": "some url"
              },
              "slot": -1
            },
            {
              "pokemon": {
                "name": "bar"
              }
            }
          ]
        }
      """
      val ws = MockWS {
        case (GET, "https://pokeapi.co/api/v2/type/some-type") =>
          Action {
            Ok(Json.parse(json))
          }
      }
      val futureResult = PokeAPIService(ws, ExecutionContext.global)
        .pokemonsOfType(Type("some-type"))
      Await.result(futureResult, 1 seconds) mustBe (
        PokemonNames(PokemonName("foo"), PokemonName("bar"))
      )
    }
  }
}
