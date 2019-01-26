package pokezen.controllers

import javax.inject._
import play.api._
import play.api.mvc._
import scala.concurrent._
import scala.util.Try

import pokezen.models.VoteEvent


trait VoteEventWritable {
  def write(event: VoteEvent): Future[Try[String]]
}

@Singleton
case class VotesController @Inject()(
  eventWriteService: VoteEventWritable,
  cc: ControllerComponents
)(
  implicit ec: ExecutionContext
) extends AbstractController(cc) {
  def upvote(pokemonName: String): Action[AnyContent] = Action { request =>
    val remoteAddress = request.headers.get("Remote-Address")
    Ok("")
  }
}
