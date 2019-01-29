import org.scalatestplus.play._

import scala.concurrent._
import scala.concurrent.duration._
import play.api._
import play.api.mvc._
import play.api.mvc.Results.BadRequest
import play.api.test.FakeRequest
import play.api.test.Helpers._

import tests.MockVotesService
import pokezen.controllers.VotesController
import pokezen.models._


class VotesControllerSpec extends PlaySpec {
  implicit val ec = ExecutionContext.global

  "VotesController.vote(pokemonName, voteValue)" should {
    "vote for pokemon in votesService" in {
      val votesService = MockVotesService()
      val controller =
        VotesController(votesService, stubControllerComponents())
      val result: Future[Result] =
        controller.vote("foo", 12)
          .apply(FakeRequest()
            .withHeaders("Remote-Address" -> "nice address"))
      Await.result(result, 1 second)

      votesService.votes mustBe Map(
        PokemonName("foo") -> Map("nice address" -> 12))
    }

    "return BadRequest when user already voted" in {
      val votesService = MockVotesService()
      val controller =
        VotesController(votesService, stubControllerComponents())
      val fakeRequest =
        FakeRequest().withHeaders("Remote-Address" -> "nice address")

      votesService.votes mustBe Map.empty[PokemonName, Map[String, Int]]

      val firstVote = controller.vote("foo", 1).apply(fakeRequest)
      Await.result(firstVote, 1 second)

      votesService.votes mustBe Map(
        PokemonName("foo") -> Map("nice address" -> 1))

      val secondVote = controller.vote("foo", 1).apply(fakeRequest)
      val result = Await.result(secondVote, 1 second)

      result mustBe BadRequest("You already voted this.")
      votesService.votes mustBe Map(
        PokemonName("foo") -> Map("nice address" -> 1))
    }

    "return BadRequest when user couldn't be identified" in {
      val controller =
        VotesController(MockVotesService(), stubControllerComponents())
      val vote = controller.vote("whatever", -1).apply(FakeRequest())
      val result = Await.result(vote, 1 second)

      result mustBe BadRequest("Server couldn't identify you.")
    }
  }
}
