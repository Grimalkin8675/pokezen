import org.scalacheck._
import org.scalacheck.Prop._

import scala.concurrent._
import scala.concurrent.duration._
import play.api.mvc._
import play.api.mvc.Results.Ok
import play.api.libs.json._
// import play.api.routing.sird._
import play.api.test._
import play.api.test.Helpers._
import play.core.server.Server
import mockws.MockWS

import pokezen.Name
import pokezen.services.pokeapi.PokeAPIService


object PokeAPIServiceSpec extends Properties("PokeAPIService") {
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

  property("searchPokemon(searchString)") = {
    val ws = MockWS {
      case (GET, "https://pokeapi.co/api/v2/pokemon") => Action {
        Ok(Json.parse(json))
      }
    }
    val futureResult =
      PokeAPIService(ws, ExecutionContext.global).searchPokemon("foo")
    Await.result(futureResult, 1 seconds) == List(Name("foo"), Name("bafooba"))
  }
}
