package pokezen.services.mongo

import javax.inject._
import scala.concurrent._
import scala.util._

import pokezen.controllers.Voteable
import pokezen.models._


case class VotesService @Inject()()(
  implicit ec: ExecutionContext
) extends Voteable {
  def vote(
    remoteAddress: String,
    name: PokemonName,
    voteValue: Int): Future[Boolean] = ???
}
