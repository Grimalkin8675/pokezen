package pokezen.controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import scala.concurrent._

import pokezen.PokemonNames


trait SearcheableService {
  def pokemons(): Future[PokemonNames]
}

@Singleton
case class SearchPokemon @Inject()(
    searchService: SearcheableService,
    cc: ControllerComponents,
    ec: ExecutionContext) extends AbstractController(cc) {
  def pokemons(): Action[AnyContent] =
    Action.async {
      searchService
        .pokemons()
        .map { names: PokemonNames => Ok(Json.toJson(names)) }(ec)
    }
}
