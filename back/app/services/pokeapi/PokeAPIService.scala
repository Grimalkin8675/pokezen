package pokezen.services.pokeapi

import javax.inject.Inject
import scala.concurrent._
import play.api.mvc._
import play.api.libs.ws._
import play.api.libs.json._

import pokezen.controllers.SearcheableService
import pokezen.PokemonNames


case class PokeAPIService @Inject()(
    ws: WSClient,
    ec: ExecutionContext) extends InjectedController
                             with SearcheableService {
  implicit val implicitEc = ec

  def pokemons(): Future[PokemonNames] = {
    def responseToNames(response: WSResponse): PokemonNames =
      Json.parse(response.body).validate[PokeAPIPokemonNames] match {
        case s: JsSuccess[PokeAPIPokemonNames] => s.get
        case e: JsError =>
          throw new Exception("problem while parsing pokeapi's response")
      }

    ws.url("https://pokeapi.co/api/v2/pokemon")
      .addHttpHeaders("Accept" -> "application/json")
      .get()
      .map(responseToNames(_).sorted)
  }
}
