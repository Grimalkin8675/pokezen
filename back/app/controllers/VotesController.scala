package pokezen.controllers

import javax.inject._
import play.api._
import play.api.mvc._
import scala.concurrent._
import scala.util._

import pokezen.models._


trait Voteable {
  def vote(
    remoteAddress: String,
    name: PokemonName,
    voteValue: Int): Future[Boolean]
}

@Singleton
case class VotesController @Inject()(
  votesService: Voteable,
  cc: ControllerComponents
)(
  implicit ec: ExecutionContext
) extends AbstractController(cc) {
  def vote(pokemonName: String, voteValue: Int): Action[AnyContent] =
    Action.async {
      request =>
        request.headers.get("Remote-Address")
          .map(remoteAddress =>
            votesService.vote(
              remoteAddress,
              PokemonName(pokemonName),
              voteValue)
            .map { added =>
              if (added) Ok("Vote added.")
              else BadRequest("You already voted this.")
            })
          .getOrElse(Future {
            BadRequest("Server couldn't identify you.")
          })
    }
}
