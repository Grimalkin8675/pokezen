package pokezen.services.pokeapi

import javax.inject.Inject
import scala.concurrent._
import play.api.mvc._
import play.api.libs.ws._
import play.api.libs.json._

import pokezen.controllers.{SearcheableService, DetaileableService}
import pokezen.{Pokemon, PokemonNames, PokemonName, Type}


case class PokeAPIService @Inject()(
    ws: WSClient,
    ec: ExecutionContext) extends InjectedController
                             with SearcheableService
                             with DetaileableService {
  implicit val implicitEc = ec

  def getAndMap[A](route: String)(implicit rds: Reads[A]): Future[A] = {
    val apiUrl = "https://pokeapi.co/api/v2"

    def validateResponse(response: WSResponse): A =
      Json.parse(response.body).validate[A] match {
        case s: JsSuccess[A] => s.get
        case e: JsError => {
          println(s"e = ${e}")
          throw new Exception("problem while parsing pokeapi's response")
        }
      }

    ws.url(s"${apiUrl}${route}")
      .addHttpHeaders("Accept" -> "application/json")
      .get
      .map(validateResponse)
  }

  def pokemons: Future[PokemonNames] =
    this.getAndMap[PokeAPIPokemonNames](s"/pokemon")
        .map(_.sorted)

  def pokemonByName(name: PokemonName): Future[Pokemon] =
    this.getAndMap[PokeAPIPokemon](s"/pokemon/${name.name}")

  def pokemonsOfType(pokemonType: Type): Future[PokemonNames] = {
    implicit val pokemonInTypeReads: Reads[PokemonName] =
      (__ \ "pokemon" \ "name").read[String].map(PokemonName.apply _)

    val namesFromTypeReads: Reads[PokemonNames] = (
      (__ \ "pokemon").read[Seq[PokemonName]].map(
        (names: Seq[PokemonName]) => PokemonNames(names: _*))
    )
    this.getAndMap[PokemonNames](
      s"/type/${pokemonType.typeName}")(namesFromTypeReads)
  }
}
