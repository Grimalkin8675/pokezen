package controllers

import javax.inject._
// import play.api._
import play.api.mvc._


@Singleton
class MainController @Inject()(
  cc: ControllerComponents) extends AbstractController(cc) {

  def index() = Action {
    request: Request[AnyContent] => Ok("Awesome backend!")
  }

}
