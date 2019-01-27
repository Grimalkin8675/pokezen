package pokezen.controllers

import javax.inject._
import play.api._
import play.api.mvc._
import scala.concurrent._
import scala.util._

import pokezen.models.{PokemonName, VoteEvent, UpVote}


trait VoteEventWritable {
  def write(event: VoteEvent): Future[Try[String]]
}

@Singleton
case class VotesController @Inject()(
  voteEventsService: VoteEventWritable,
  cc: ControllerComponents
)(
  implicit ec: ExecutionContext
) extends AbstractController(cc) {
  def upvote(pokemonName: String): Action[AnyContent] = Action.async {
    request =>
      request.headers.get("Remote-Address")
        .map(remoteAddress =>
          voteEventsService.write(
            UpVote(remoteAddress, PokemonName(pokemonName)))
          .map {
            case Success(message) => Ok(message)
            case Failure(error) => BadRequest(error.toString)
            // improve this, as it could also be a ServerError
          })
        .getOrElse(Future {
          val message = "Server couldn't identify you."
          ???
        })
  }
}
