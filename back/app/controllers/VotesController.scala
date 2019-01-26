package pokezen.controllers

import javax.inject._
import play.api._
import play.api.mvc._
import scala.concurrent._


@Singleton
case class VotesController @Inject()(
  cc: ControllerComponents
)(
  implicit ec: ExecutionContext
) extends AbstractController(cc) {
  def upvote(pokemonName: String): Action[AnyContent] = Action { Ok("") }
}
