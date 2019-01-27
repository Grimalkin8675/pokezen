package pokezen.services.mongo

import javax.inject._
import scala.concurrent._
import scala.util._

import pokezen.controllers.VoteEventWritable
import pokezen.models.VoteEvent


case class EventsService @Inject()()(
  implicit ec: ExecutionContext
) extends VoteEventWritable {
  def write(event: VoteEvent): Future[Try[String]] = ???

  def events: Future[List[VoteEvent]] = ???
}
