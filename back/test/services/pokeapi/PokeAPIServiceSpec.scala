import org.scalatestplus.play._

import scala.concurrent._
import scala.concurrent.duration._
import play.api.mvc._
import play.api.mvc.Results.{Ok, NotFound}
import play.api.libs.json._
import play.api.test._
import play.api.test.Helpers._
import play.core.server.Server
import mockws.MockWS

import pokezen._
import pokezen.controllers.PokemonsService
import pokezen.services.pokeapi.PokeAPIService


class PokeAPIServiceSpec extends PlaySpec {
  implicit val ec = ExecutionContext.global

  "PokeAPIService.pokemons" should {
    "return pokemons' names" in {
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
        case (GET, "https://pokeapi.co/api/v2/pokemon?limit=-1") => Action {
          Ok(Json.parse(json))
        }
      }
      val futureResult = PokeAPIService(ws).pokemons
      val res = Await.result(futureResult, 1 seconds)
      res.isDefined mustBe true
      res.get.names.size mustBe 3
      res.get.names.contains(PokemonName("foo")) mustBe true
      res.get.names.contains(PokemonName("bar")) mustBe true
      res.get.names.contains(PokemonName("bafooba")) mustBe true
    }

    "return sorted pokemons' names" in {
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
        case (GET, "https://pokeapi.co/api/v2/pokemon?limit=-1") => Action {
          Ok(Json.parse(json))
        }
      }
      val futureResult = PokeAPIService(ws).pokemons
      Await.result(futureResult, 1 seconds) mustBe Some(PokemonNames(
        PokemonName("abc"),
        PokemonName("abcd"),
        PokemonName("def"),
        PokemonName("ghi")
      ))
    }

    "handle not found api" in {
      val ws = MockWS {
        case (GET, "https://pokeapi.co/api/v2/pokemon?limit=-1") => Action {
          NotFound
        }
      }
      val futureResult = PokeAPIService(ws).pokemons
      Await.result(futureResult, 1 seconds) mustBe None
    }
  }


  "PokeAPIService.pokemonByName(pokemonName)" should {
    "return a Pokemon" in {
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
      val futureResult = PokeAPIService(ws)
        .pokemonByName(PokemonName("some-pokemon-name"))
      Await.result(futureResult, 1 seconds) mustBe
        Some(Pokemon(
          PokemonName("foo"),
          Some(ImageURL("foo image url")),
          Types(Type("poison"), Type("grass")),
          Stats(Stat("speed", 45), Stat("defense", 65))))
    }
  }


  "PokeAPIService.pokemonsOfType(type)" should {
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
      val futureResult = PokeAPIService(ws)
        .pokemonsOfType(Type("some-type"))
      Await.result(futureResult, 1 seconds) mustBe
        Some(PokemonNames(PokemonName("foo"), PokemonName("bar")))
    }
  }

  "PokeAPIService" should {
    "extend PokemonsService" in {
      val ws = MockWS(PartialFunction.empty)
      PokeAPIService(ws)
        .isInstanceOf[PokemonsService] mustBe true
    }
  }
}
