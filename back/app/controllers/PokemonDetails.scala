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
    detailsService: DetaileableService,
    cc: ControllerComponents,
    ec: ExecutionContext) extends AbstractController(cc) {
  implicit val implicitEc = ec

  def pokemon(pokemonName: String): Action[AnyContent] = {
    Action {
      Ok(Json.parse("""
        {
          "name": "bar",
          "image": "bar_image",
          "types": ["fire", "air"],
          "base_stats": [
            {
              "name": "speed",
              "value": 70
            },
            {
              "name": "defense",
              "base": 50
            }
          ],
          "stats": [
            {
              "name": "speed",
              "comparisons": {
                "fire": 5,
                "air": -10
              }
            },
            {
              "name": "defense",
              "comparisons": {
                "fire": -15,
                "air": 10
              }
            }
          ]
        }
      """))
    }
  }
}
