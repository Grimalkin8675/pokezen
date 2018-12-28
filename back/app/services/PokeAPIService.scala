package pokezen.services.pokeapi

import javax.inject.Inject
import scala.concurrent._
import play.api.mvc._
import play.api.libs.ws._
// import play.api.libs.json._

import pokezen.controllers.SearcheableService
import pokezen.Name


case class PokeAPIService @Inject()(
    ws: WSClient,
    ec: ExecutionContext) extends InjectedController
                             with SearcheableService {
  implicit val implicitEc = ec

  def searchPokemon(searchString: String): Future[List[Name]] = {
    ???
  }
}
