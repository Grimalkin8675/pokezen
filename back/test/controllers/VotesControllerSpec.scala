import org.scalatestplus.play._

import scala.concurrent._
import scala.concurrent.duration._
import play.api.mvc._
import play.api.test.FakeRequest
import play.api.test.Helpers._

import tests.MockVoteEventsService
import pokezen.controllers.VotesController
import pokezen.models.{UpVote, PokemonName}


class VotesControllerSpec extends PlaySpec {
  implicit val ec = ExecutionContext.global

  "VotesController.upvote(pokemonName)" should {
    "add an upvote event to eventsService" in {
      val voteEventsService = MockVoteEventsService()
      val controller =
        VotesController(voteEventsService, stubControllerComponents())
      val result: Future[Result] =
        controller.upvote("foo")
          .apply(FakeRequest()
            .withHeaders("Remote-Address" -> "nice address"))
      Await.result(result, 1 second)

      voteEventsService.events.size mustBe 1
      voteEventsService.events.head.isInstanceOf[UpVote] mustBe true
      voteEventsService.events.head match {
        case upVote: UpVote =>
          upVote.pokemonName mustBe PokemonName("foo")
        case _ => Unit // should never happen
      }
    }

    "return BadRequest when user already voted" in {
      val voteEventsService = MockVoteEventsService()
      val controller =
        VotesController(voteEventsService, stubControllerComponents())
      val fakeRequest =
        FakeRequest().withHeaders("Remote-Address" -> "nice address")

      voteEventsService.events.size mustBe 0

      val firstUpVote = controller.upvote("foo").apply(fakeRequest)
      Await.result(firstUpVote, 1 second)

      voteEventsService.events.size mustBe 1

      val secondUpVote = controller.upvote("foo").apply(fakeRequest)
      val result = Await.result(secondUpVote, 1 second)

      result.header.status mustBe BAD_REQUEST
      voteEventsService.events.size mustBe 1
    }

    "return InternalServerError when user couldn't be identified" in {
      val controller =
        VotesController(MockVoteEventsService(), stubControllerComponents())
      val upVote = controller.upvote("whatever").apply(FakeRequest())
      val result = Await.result(upVote, 1 second)

      result.header.status mustBe INTERNAL_SERVER_ERROR
    }
  }
}
