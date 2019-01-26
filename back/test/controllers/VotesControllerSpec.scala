import org.scalatestplus.play._

import scala.concurrent._
import scala.concurrent.duration._
import play.api.mvc._
import play.api.test.FakeRequest
import play.api.test.Helpers.stubControllerComponents

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
      }
    }
  }
}
