import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._


class ApplicationSpec extends PlaySpec with GuiceOneAppPerTest {
  "Routes" should {
    "send 404 on a bad request (/)" in {
      route(app, FakeRequest(GET, "/")).map(status(_)) mustBe Some(NOT_FOUND)
    }

    "send 404 on a bad request (/boum)" in {
      route(app, FakeRequest(GET, "/boum")).map(status(_)) mustBe Some(NOT_FOUND)
    }
  }
}
