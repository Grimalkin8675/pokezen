package tests

import scala.concurrent._
import scala.util._

import pokezen.controllers.VoteEventWritable
import pokezen.models.VoteEvent


case class MockVoteEventsService() extends VoteEventWritable {
  implicit val ec = ExecutionContext.global

  var events: List[VoteEvent] = List.empty[VoteEvent]

  def write(event: VoteEvent): Future[Try[String]] = Future {
    if (events.contains(event)) Failure(new Exception("you already voted"))
    else {
      events :+= event
      Success("event added")
    }
  }
}
