package pokezen.controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import scala.concurrent._

import pokezen.Name


trait SearcheableService {
  def searchPokemon(searchString: String): Future[List[Name]]
}

@Singleton
case class SearchController @Inject()(
    searchService: SearcheableService,
    cc: ControllerComponents,
    ec: ExecutionContext) extends AbstractController(cc) {
  def searchPokemon(searchString: String): Action[AnyContent] =
    Action.async {
      searchService
        .searchPokemon(searchString)
        .map {
          names: List[Name] => Ok(Json.toJson(names))
        }(ec)
    }
}
