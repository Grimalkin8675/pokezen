package pokezen.controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import scala.concurrent._
import play.api.libs.json._

import pokezen.{Pokemon, PokemonNames, PokemonName, Type}


trait DetaileableService {
  def pokemonByName(name: PokemonName): Future[Pokemon]
  def pokemonsOfType(pokemonType: Type): Future[PokemonNames]
}

@Singleton
case class PokemonDetails @Inject()(
    cc: ControllerComponents,
    ec: ExecutionContext) extends AbstractController(cc) {
  def pokemon(pokemonName: String): Action[AnyContent] =
    Action { _ =>
      Ok(Json.parse("""
        {
          "name": "bar",
          "image": "bar_image",
          "types": [
            "fire",
            "air"
          ],
          "stats": [
            {
              "name": "speed",
              "base": 70,
              "comparison": {
                "fire": 5,
                "air": -10
              }
            },
            {
              "name": "defense",
              "base": 50,
              "comparison": {
                "fire": -15,
                "air": 10
              }
            }
          ]
        }
      """))
    }
}
