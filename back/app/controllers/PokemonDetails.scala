package pokezen.controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import scala.concurrent._

import pokezen.Name


@Singleton
case class PokemonDetails @Inject()(
    cc: ControllerComponents,
    ec: ExecutionContext) extends AbstractController(cc) {
  def pokemon(pokemonName: String): Action[AnyContent] =
    Action { _ => Ok("") }
}
