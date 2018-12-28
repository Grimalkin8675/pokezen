package pokezen.services.pokeapi

import javax.inject.Inject
import scala.concurrent._
import play.api.mvc._
import play.api.libs.ws._
import play.api.libs.json._

import pokezen.controllers.SearcheableService
import pokezen.Name


case class PokeAPIService @Inject()(
    ws: WSClient,
    ec: ExecutionContext) extends InjectedController
                             with SearcheableService {
  implicit val implicitEc = ec

  def searchPokemon(searchString: String): Future[List[Name]] = {
    def responseToNames(response: WSResponse): List[Name] =
      Json.parse(response.body).validate[PokeAPIPokemons] match {
        case s: JsSuccess[PokeAPIPokemons] => s.get.toNames
        case e: JsError => throw new Exception("problem while parsing pokeapi's response")
      }

    def matchingName(searchString: String)(name: Name): Boolean =
      name.contains(searchString)

    ws.url("https://pokeapi.co/api/v2/pokemon")
      .addHttpHeaders("Accept" -> "application/json")
      .get()
      .map(responseToNames)
      .map(_.filter(matchingName(searchString)))
  }
}
