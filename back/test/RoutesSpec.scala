import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._
import play.api.inject.bind
import play.api.inject.guice.GuiceApplicationBuilder

import tests.MockSearchService
import pokezen.controllers.SearcheableService


class RoutesSpec extends PlaySpec with GuiceOneAppPerTest {
  "Routes" should {
    "send 404 on a bad request (/)" in {
      route(app, FakeRequest(GET, "/"))
        .map(status(_)) mustBe Some(NOT_FOUND)
    }

    "send 404 on a bad request (/boum)" in {
      route(app, FakeRequest(GET, "/boum"))
        .map(status(_)) mustBe Some(NOT_FOUND)
    }
  }
}


class RoutesWithMockSpec extends PlaySpec with GuiceOneAppPerTest {
  override def fakeApplication() = new GuiceApplicationBuilder()
    .overrides(bind[SearcheableService].to[MockSearchService])
    .build()

  "Routes with mock" should {
    "send 200 on a valid request (/pokemons)" in {
      route(app, FakeRequest(GET, "/pokemons"))
        .map(status(_)) mustBe Some(OK)
    }
  }
}
