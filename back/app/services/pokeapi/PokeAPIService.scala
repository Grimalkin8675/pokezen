package pokezen.services.pokeapi

import javax.inject.Inject
import scala.concurrent._
import play.api.mvc._
import play.api.libs.ws._
import play.api.libs.json._

import pokezen.controllers.SearcheableService
import pokezen.{Name, NameOrdering}


case class PokeAPIService @Inject()(
    ws: WSClient,
    ec: ExecutionContext) extends InjectedController
                             with SearcheableService {
  implicit val implicitEc = ec

  def pokemons(): Future[List[Name]] = {
    def responseToNames(response: WSResponse): List[Name] =
      Json.parse(response.body).validate[PokeAPIPokemons] match {
        case s: JsSuccess[PokeAPIPokemons] => s.get.toNames
        case e: JsError =>
          throw new Exception("problem while parsing pokeapi's response")
      }

    ws.url("https://pokeapi.co/api/v2/pokemon")
      .addHttpHeaders("Accept" -> "application/json")
      .get()
      .map(responseToNames(_).sorted(NameOrdering))
  }
}
